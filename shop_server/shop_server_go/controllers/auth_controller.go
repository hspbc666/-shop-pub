package controllers

import (
	"github.com/gin-gonic/gin"
	"github.com/sumitroajiprabowo/gin-gorm-jwt-mysql/beans"
	"github.com/sumitroajiprabowo/gin-gorm-jwt-mysql/entity"
	"github.com/sumitroajiprabowo/gin-gorm-jwt-mysql/helper"
	"github.com/sumitroajiprabowo/gin-gorm-jwt-mysql/services"
	"net/http"
)

type AuthController interface {
	Login(c *gin.Context)    // Login
	Register(c *gin.Context) // Register
}

type authController struct {
	authService services.AuthService // inject auth service
	jwtService  services.JWTService  // inject jwt service
}

func NewAuthController(authService services.AuthService, jwtService services.JWTService) AuthController {
	return &authController{
		authService: authService, // inject auth service
		jwtService:  jwtService,  // inject jwt service
	}
}

func (c *authController) Login(ctx *gin.Context) {
	var loginDTO beans.LoginRequest // create new instance of LoginRequest
	errDTO := ctx.ShouldBind(&loginDTO)
	if errDTO != nil {
		response := helper.ErrorsResponse(http.StatusBadRequest, "Failed to process request", errDTO.Error(), helper.EmptyObject{})
		ctx.AbortWithStatusJSON(http.StatusBadRequest, response)
		return
	}

	authResult := c.authService.VerifyCredential(loginDTO.Name, loginDTO.Password)

	if v, ok := authResult.(entity.User); ok {
		//generatedToken := c.jwtService.GenerateToken(strconv.Itoa(int(v.ID)))
		//v.Token = generatedToken
		response := helper.SuccessResponse(http.StatusOK, "Login Success", v)
		ctx.JSON(http.StatusOK, response)
		return
	}

	response := helper.ErrorsResponse(http.StatusBadRequest, "Failed to process request", "Invalid Credential", helper.EmptyObject{})
	ctx.AbortWithStatusJSON(http.StatusUnauthorized, response)
}

// Register is a function for register
func (c *authController) Register(ctx *gin.Context) {

	// create new instance of RegisterRequest
	var request beans.RegisterRequest

	// bind the request with the request body
	errDTO := ctx.ShouldBind(&request)

	// Check if there is any error in binding
	if errDTO != nil {
		response := helper.ErrorsResponse(http.StatusBadRequest, "Failed to process request", errDTO.Error(), helper.EmptyObject{})
		ctx.AbortWithStatusJSON(http.StatusBadRequest, response)
		return
	}

	if !c.authService.IsDuplicateName(request.Name) {
		response := helper.ErrorsResponse(http.StatusConflict, "Failed to process request", "user already registered", helper.EmptyObject{})
		ctx.AbortWithStatusJSON(http.StatusConflict, response)
		return
	} else {
		createdUser := c.authService.CreateUser(request) // create new user

		// generate token
		//token := c.jwtService.GenerateToken(strconv.Itoa(int(createdUser.ID)))

		//// set token to the user
		//createdUser.Token = token

		// response with the user data and token
		response := helper.SuccessResponse(http.StatusCreated, "Register Success", createdUser)

		// return the response
		ctx.JSON(http.StatusCreated, response)
	}
}
