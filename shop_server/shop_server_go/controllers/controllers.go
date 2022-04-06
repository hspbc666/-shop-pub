package controllers

import (
	"fmt"
	"github.com/dgrijalva/jwt-go"
	"github.com/gin-gonic/gin"
	"github.com/sumitroajiprabowo/gin-gorm-jwt-mysql/beans"
	"github.com/sumitroajiprabowo/gin-gorm-jwt-mysql/services"
	"net/http"
	"strconv"
)

type BizController interface {
	GetAll(c *gin.Context)       // Get All Data Book
	GetByID(c *gin.Context)      // Get Data Book By ID
	GetAllMyBook(c *gin.Context) // Get All Data Book By User
	QueryCategory(c *gin.Context)
	QueryGoods(c *gin.Context)
	CreateMyBook(c *gin.Context) // Create Data Book By User
	UpdateMyBook(c *gin.Context) // Update Data Book By User
	DeleteMyBook(c *gin.Context) // Delete Data Book By User
}

type bizController struct {
	userService services.UserService
	bookService services.BookService // BookService for CRUD Book
	jwtService  services.JWTService
}

func NewBizController(userService services.UserService, bookService services.BookService, jwtService services.JWTService) *bizController {
	return &bizController{
		userService: userService,
		bookService: bookService,
		jwtService:  jwtService,
	}
}

func (c *bizController) GetAll(ctx *gin.Context) {
	var books []beans.Book = c.bookService.GetAll()
	result := beans.SuccessResponse(http.StatusOK, "Get All Data Book", books)
	ctx.JSON(http.StatusOK, result) // Return Response
}

func (c *bizController) GetByID(ctx *gin.Context) {
	bookID, err := strconv.ParseUint(ctx.Param("id"), 10, 64)
	if err != nil {
		response := beans.ErrorsResponse(http.StatusBadRequest, "Book Not Found", beans.EmptyObject{})
		ctx.AbortWithStatusJSON(http.StatusBadRequest, response)
		return
	}

	var book beans.Book = c.bookService.GetByID(bookID)

	if book == (beans.Book{}) { // Check book is empty or not
		// Return error response with status code 404 and message book not found
		response := beans.ErrorsResponse(http.StatusNotFound, "Book Not Found", beans.EmptyObject{})

		// Return response with status code 404 and message book not found
		ctx.AbortWithStatusJSON(http.StatusNotFound, response)

		return
	} else { // If book is not empty

		// Return success response with status code 200 and data book
		response := beans.SuccessResponse(http.StatusOK, "Get Data Book", book)

		// Return Response
		ctx.JSON(http.StatusOK, response)
	}
}

func (c *bizController) QueryCategory(ctx *gin.Context) {
	var books = c.bookService.QueryCategory()
	result := beans.SuccessResponse(http.StatusOK, "Get All Data Book", books)
	ctx.JSON(http.StatusOK, result) // Return Response
}

func (c *bizController) QueryGoods(ctx *gin.Context) {
	var books = c.bookService.QueryCategory()
	result := beans.SuccessResponse(http.StatusOK, "Get All Data Book", books)
	ctx.JSON(http.StatusOK, result) // Return Response
}

// GetAllMyBook function for get all data book by user
func (c *bizController) GetAllMyBook(ctx *gin.Context) {
	var book []beans.Book = c.bookService.GetAllMyBook()
	response := beans.SuccessResponse(http.StatusOK, "Get All Data Book", book)
	ctx.JSON(http.StatusOK, response)
}

// CreateMyBook function for create data book by user
func (c *bizController) CreateMyBook(ctx *gin.Context) {

	// Create bookCreateDTO variable for binding data from request body
	var bookCreateDTO beans.BookCreateDTORequest

	// Bind data from request body to bookCreateDTO variable
	errDTO := ctx.ShouldBind(&bookCreateDTO)

	// Check error from ctx.ShouldBind
	if errDTO != nil {
		response := beans.ErrorsResponse(http.StatusBadRequest, "Invalid data", beans.EmptyObject{})
		ctx.AbortWithStatusJSON(http.StatusBadRequest, response)
		return
	}

	// Get Authorization from header
	authHeader := ctx.GetHeader("Authorization")

	// Get token from Authorization
	userID := c.getUserIDByToken(authHeader)

	// Create Book variable for binding data from bookCreateDTO variable to Book
	id, err := strconv.ParseUint(userID, 10, 64)

	// Check error from strconv.ParseUint
	if err == nil {
		bookCreateDTO.UserID = id
	}

	// Create Book variable for binding data from bookCreateDTO variable to Book
	result := c.bookService.CreateMyBook(bookCreateDTO)

	// response variable for return response with status code and message
	response := beans.SuccessResponse(http.StatusCreated, "Create Data Book", result)

	// Return Response
	ctx.JSON(http.StatusCreated, response)

}

// UpdateMyBook function for update data book by user
func (c *bizController) UpdateMyBook(ctx *gin.Context) {

	// Create bookUpdateDTO variable for binding data from request body
	var bookUpdateDTO beans.BookUpdateDTORequest

	// Bind data from request body to bookUpdateDTO variable
	errDTO := ctx.ShouldBind(&bookUpdateDTO)

	// Check error from ctx.ShouldBind
	if errDTO != nil {
		result := beans.ErrorsResponse(http.StatusBadRequest, "Invalid data", beans.EmptyObject{})
		ctx.AbortWithStatusJSON(http.StatusBadRequest, result)
		return
	}

	// Get Authorization from header
	authHeader := ctx.GetHeader("Authorization")

	// Get token from Authorization
	token, errToken := c.jwtService.ValidateToken(authHeader)

	// Check error from jwtService.ValidateToken
	if errToken != nil {
		panic(errToken.Error())
	}

	// Get userID from token
	claims := token.Claims.(jwt.MapClaims)

	// Get userID from token and assign to userID variable
	userID := fmt.Sprintf("%v", claims["user_id"])

	// Create Book variable for binding data from bookUpdateDTO variable to Book
	if c.bookService.IsAllowedActionBook(userID, bookUpdateDTO.ID) {

		id, errID := strconv.ParseUint(userID, 10, 64) // Parse userID to uint64

		// Check error from strconv.ParseUint
		if errID == nil {
			bookUpdateDTO.UserID = id
		}

		// Update data book by user
		result := c.bookService.UpdateMyBook(bookUpdateDTO)

		// response variable for return response with status code and message
		response := beans.SuccessResponse(http.StatusOK, "Update Data Book", result)

		// Return Response
		ctx.JSON(http.StatusOK, response)
	} else {
		/*
			If user is not allowed to update data book
			Return error response with status code 403 and message user is not allowed to update data book
		*/
		response := beans.ErrorsResponse(http.StatusForbidden, "You are not allowed to update this book", beans.EmptyObject{})
		// Return Response
		ctx.AbortWithStatusJSON(http.StatusForbidden, response)
	}
}

// DeleteMyBook function for delete data book by user
func (c *bizController) DeleteMyBook(ctx *gin.Context) {

	var book beans.Book // Create Book variable from entity.Book

	// Get id from url parameter with key id
	id, err := strconv.ParseUint(ctx.Param("id"), 10, 64)

	// Check error from strconv.ParseUint
	if err != nil {
		response := beans.ErrorsResponse(http.StatusBadRequest, "Book Not Found", beans.EmptyObject{})
		ctx.AbortWithStatusJSON(http.StatusBadRequest, response)
		return
	}

	book.ID = id // Assign id to Book.ID

	// Get Authorization from header
	authHeader := ctx.GetHeader("Authorization")

	// Get token from Authorization
	token, errToken := c.jwtService.ValidateToken(authHeader)

	// Check error from jwtService.ValidateToken
	if errToken != nil {
		panic(errToken.Error())
	}

	// Get userID from token
	claims := token.Claims.(jwt.MapClaims)

	// Get userID from token and assign to userID variable
	userID := fmt.Sprintf("%v", claims["user_id"])

	// Check if user is allowed to delete data book
	if c.bookService.IsAllowedActionBook(userID, book.ID) {

		c.bookService.DeleteMyBook(book) // Delete data book by user

		// response variable for return response with status code and message
		response := beans.SuccessResponse(http.StatusOK, "Delete Data Book", book)

		// Return Response
		ctx.JSON(http.StatusOK, response)
	} else { // If user is not allowed to delete data book

		// response variable for return response with status code and message
		response := beans.ErrorsResponse(http.StatusForbidden, "You are not allowed to delete this book", beans.EmptyObject{})

		// Return Response
		ctx.AbortWithStatusJSON(http.StatusForbidden, response)
	}
}

// GetMyBookByID function for get data book by user
func (c *bizController) getUserIDByToken(token string) string {

	// Get token from Authorization
	myToken, err := c.jwtService.ValidateToken(token)

	// Check error from jwtService.ValidateToken
	if err != nil {
		panic(err.Error())
	}

	// Get userID from token
	claims := myToken.Claims.(jwt.MapClaims)

	// Get userID from token and assign to userID variable
	return claims["user_id"].(string)
}
