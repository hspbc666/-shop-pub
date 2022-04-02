package main

import (
	"github.com/gin-gonic/gin"
	"github.com/sumitroajiprabowo/gin-gorm-jwt-mysql/config"
	"github.com/sumitroajiprabowo/gin-gorm-jwt-mysql/controllers"
	"github.com/sumitroajiprabowo/gin-gorm-jwt-mysql/middleware"
	"github.com/sumitroajiprabowo/gin-gorm-jwt-mysql/repository"
	"github.com/sumitroajiprabowo/gin-gorm-jwt-mysql/services"
)

var (
	db             = config.SetupDatabase()
	userRepository = repository.NewUserRepository(db)
	bookRepository = repository.NewBookRepository(db)
	jwtService     = services.NewJWTService()
	userService    = services.NewUserService(userRepository)
	bookService    = services.NewBookService(bookRepository)
	authService    = services.NewAuthService(userRepository)
	authController = controllers.NewAuthController(authService, jwtService)
	bizController  = controllers.NewBizController(userService, bookService, jwtService)
)

func main() {
	defer config.CloseDatabaseConnection(db)
	r := gin.Default()

	authRoutes := r.Group("/api/auth")
	{
		authRoutes.POST("/login", authController.Login)
		authRoutes.POST("/register", authController.Register)
	}

	userRoutes := r.Group("/api/user", middleware.AuthorizeJWT(jwtService))
	{
		userRoutes.GET("/profile", bizController.GetUser)
		userRoutes.PUT("/profile", bizController.UpdateUser)
	}

	bookRoutes := r.Group("api/books", middleware.AuthorizeJWT(jwtService))
	{
		bookRoutes.GET("/", bizController.GetAllMyBook)
		bookRoutes.GET("/:id", bizController.GetByID)
		bookRoutes.POST("/", bizController.CreateMyBook)
		bookRoutes.PUT("/:id", bizController.UpdateMyBook)
		bookRoutes.DELETE("/:id", bizController.DeleteMyBook)
	}

	publicBookRoute := r.Group("/api/public/books")
	{
		publicBookRoute.GET("/", bizController.GetAll)
		publicBookRoute.GET("/:id", bizController.GetByID)
	}

	r.Run(":8080")

}
