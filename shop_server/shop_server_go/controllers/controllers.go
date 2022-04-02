package controllers

import (
	"fmt"
	"github.com/dgrijalva/jwt-go"
	"github.com/gin-gonic/gin"
	"github.com/sumitroajiprabowo/gin-gorm-jwt-mysql/dto"
	"github.com/sumitroajiprabowo/gin-gorm-jwt-mysql/entity"
	"github.com/sumitroajiprabowo/gin-gorm-jwt-mysql/helper"
	"github.com/sumitroajiprabowo/gin-gorm-jwt-mysql/services"
	"net/http"
	"strconv"
)

// UserController is a struct for user controller
type BizController interface {
	UpdateUser(c *gin.Context)   // UpdateUser is a function for update user
	GetUser(c *gin.Context)      // GetUser is a function for get user
	GetAll(c *gin.Context)       // Get All Data Book
	GetByID(c *gin.Context)      // Get Data Book By ID
	GetAllMyBook(c *gin.Context) // Get All Data Book By User
	CreateMyBook(c *gin.Context) // Create Data Book By User
	UpdateMyBook(c *gin.Context) // Update Data Book By User
	DeleteMyBook(c *gin.Context) // Delete Data Book By User
}

// userController is a struct for user controller
type bizController struct {
	// userService is a new instance of UserService
	userService services.UserService
	bookService services.BookService // BookService for CRUD Book
	// jwtService is a new instance of JWTService
	jwtService services.JWTService
}

// NewUserController is a function for create new instance of UserController
func NewBizController(userService services.UserService, bookService services.BookService, jwtService services.JWTService) BizController {
	return &bizController{
		// userService is a new instance of UserService
		userService: userService,
		bookService: bookService,
		// jwtService is a new instance of JWTService
		jwtService: jwtService,
	}
}

// UpdateUser is a function for update user
func (c *bizController) UpdateUser(ctx *gin.Context) {

	// userUpdateDTO is a new instance of UserUpdateDTORequest
	var userUpdateDTO dto.UserUpdateDTORequest

	// Bind the userUpdateDTO with the request body
	errDTO := ctx.ShouldBind(&userUpdateDTO)

	// Check if there is any error in binding
	if errDTO != nil {
		response := helper.ErrorsResponse(http.StatusBadRequest, "Failed to process request", errDTO.Error(), helper.EmptyObject{})
		ctx.AbortWithStatusJSON(http.StatusBadRequest, response)
		return
	}

	// Get the token from the header of the request
	authHeader := ctx.GetHeader("Authorization")
	// Validate the token
	token, errToken := c.jwtService.ValidateToken(authHeader)

	// Check if there is any error in validating token
	if errToken != nil {
		response := helper.ErrorsResponse(http.StatusUnauthorized, "Failed to process request", errToken.Error(), helper.EmptyObject{})
		ctx.AbortWithStatusJSON(http.StatusUnauthorized, response)
		return
	}

	claims := token.Claims.(jwt.MapClaims) // Get the claims of the token

	// Get the user id from the claims
	userId, err := strconv.ParseUint(claims["user_id"].(string), 10, 64)

	// Check if there is any error in parsing user id
	if err != nil {
		response := helper.ErrorsResponse(http.StatusBadRequest, "Failed to process request", err.Error(), helper.EmptyObject{})
		ctx.AbortWithStatusJSON(http.StatusBadRequest, response)
		return
	}

	userUpdateDTO.ID = userId // Get the user from the database

	user := c.userService.UpdateUser(userUpdateDTO) // Update the user

	response := helper.SuccessResponse(http.StatusOK, "Update User Success", user) // Create the response for the user

	ctx.JSON(http.StatusOK, response) // Return the response
}

// GetUser is a function for get user
func (c *bizController) GetUser(ctx *gin.Context) {

	// Get the token from the header of the request
	authHeader := ctx.GetHeader("Authorization")

	// Validate the token
	token, errToken := c.jwtService.ValidateToken(authHeader)

	// Check if there is any error in validating token
	if errToken != nil {
		response := helper.ErrorsResponse(http.StatusUnauthorized, "Failed to process request", errToken.Error(), helper.EmptyObject{})
		ctx.AbortWithStatusJSON(http.StatusUnauthorized, response)
		return
	}

	// Get the claims of the token
	claims := token.Claims.(jwt.MapClaims)

	// Get the user id from the claims
	userId, err := strconv.ParseUint(claims["user_id"].(string), 10, 64)

	// Check if there is any error in parsing user id
	if err != nil {
		response := helper.ErrorsResponse(http.StatusBadRequest, "Failed to process request", err.Error(), helper.EmptyObject{})
		ctx.AbortWithStatusJSON(http.StatusBadRequest, response)
		return
	}

	// Get the user from the database with the user id
	user := c.userService.GetUser(int64(userId))

	// Create the response for the user
	response := helper.SuccessResponse(http.StatusOK, "Get User Success", user)

	// Return the response
	ctx.JSON(http.StatusOK, response)
}

func (c *bizController) GetAll(ctx *gin.Context) {
	/*
		Get All Data Book from BookService and assign to books variable for get all data book
	*/
	var books []entity.Book = c.bookService.GetAll()

	// Return success response with status code 200 and data books
	result := helper.SuccessResponse(http.StatusOK, "Get All Data Book", books)

	ctx.JSON(http.StatusOK, result) // Return Response
}

// GetByID function for get data book by id
func (c *bizController) GetByID(ctx *gin.Context) {

	// Get id from url parameter with key id
	bookID, err := strconv.ParseUint(ctx.Param("id"), 10, 64)

	// Check error from strconv.ParseUint
	if err != nil {
		response := helper.ErrorsResponse(http.StatusBadRequest, "Book Not Found", err.Error(), helper.EmptyObject{})
		ctx.AbortWithStatusJSON(http.StatusBadRequest, response)
		return
	}

	/*
		Get data book by id from BookService and assign to book variable for get data book by id from BookService and assign to book variable
	*/
	var book entity.Book = c.bookService.GetByID(bookID)

	if book == (entity.Book{}) { // Check book is empty or not
		// Return error response with status code 404 and message book not found
		response := helper.ErrorsResponse(http.StatusNotFound, "Book Not Found", "", helper.EmptyObject{})

		// Return response with status code 404 and message book not found
		ctx.AbortWithStatusJSON(http.StatusNotFound, response)

		return
	} else { // If book is not empty

		// Return success response with status code 200 and data book
		response := helper.SuccessResponse(http.StatusOK, "Get Data Book", book)

		// Return Response
		ctx.JSON(http.StatusOK, response)
	}
}

// GetAllMyBook function for get all data book by user
func (c *bizController) GetAllMyBook(ctx *gin.Context) {

	//Get All Data Book By User from BookService and assign to books variable
	var book []entity.Book = c.bookService.GetAllMyBook()

	// Return success response with status code 200 and data books
	response := helper.SuccessResponse(http.StatusOK, "Get All Data Book", book)

	// Return Response
	ctx.JSON(http.StatusOK, response)
}

// CreateMyBook function for create data book by user
func (c *bizController) CreateMyBook(ctx *gin.Context) {

	// Create bookCreateDTO variable for binding data from request body
	var bookCreateDTO dto.BookCreateDTORequest

	// Bind data from request body to bookCreateDTO variable
	errDTO := ctx.ShouldBind(&bookCreateDTO)

	// Check error from ctx.ShouldBind
	if errDTO != nil {
		response := helper.ErrorsResponse(http.StatusBadRequest, "Invalid data", errDTO.Error(), helper.EmptyObject{})
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
	response := helper.SuccessResponse(http.StatusCreated, "Create Data Book", result)

	// Return Response
	ctx.JSON(http.StatusCreated, response)

}

// UpdateMyBook function for update data book by user
func (c *bizController) UpdateMyBook(ctx *gin.Context) {

	// Create bookUpdateDTO variable for binding data from request body
	var bookUpdateDTO dto.BookUpdateDTORequest

	// Bind data from request body to bookUpdateDTO variable
	errDTO := ctx.ShouldBind(&bookUpdateDTO)

	// Check error from ctx.ShouldBind
	if errDTO != nil {
		result := helper.ErrorsResponse(http.StatusBadRequest, "Invalid data", errDTO.Error(), helper.EmptyObject{})
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
		response := helper.SuccessResponse(http.StatusOK, "Update Data Book", result)

		// Return Response
		ctx.JSON(http.StatusOK, response)
	} else {
		/*
			If user is not allowed to update data book
			Return error response with status code 403 and message user is not allowed to update data book
		*/
		response := helper.ErrorsResponse(http.StatusForbidden, "Forbidden", "You are not allowed to update this book", helper.EmptyObject{})
		// Return Response
		ctx.AbortWithStatusJSON(http.StatusForbidden, response)
	}
}

// DeleteMyBook function for delete data book by user
func (c *bizController) DeleteMyBook(ctx *gin.Context) {

	var book entity.Book // Create Book variable from entity.Book

	// Get id from url parameter with key id
	id, err := strconv.ParseUint(ctx.Param("id"), 10, 64)

	// Check error from strconv.ParseUint
	if err != nil {
		response := helper.ErrorsResponse(http.StatusBadRequest, "Book Not Found", err.Error(), helper.EmptyObject{})
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
		response := helper.SuccessResponse(http.StatusOK, "Delete Data Book", book)

		// Return Response
		ctx.JSON(http.StatusOK, response)
	} else { // If user is not allowed to delete data book

		// response variable for return response with status code and message
		response := helper.ErrorsResponse(http.StatusForbidden, "Forbidden", "You are not allowed to delete this book", helper.EmptyObject{})

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
