package services

import (
	"log"

	"github.com/mashingan/smapping"
	"github.com/sumitroajiprabowo/gin-gorm-jwt-mysql/dto"
	"github.com/sumitroajiprabowo/gin-gorm-jwt-mysql/entity"
	"github.com/sumitroajiprabowo/gin-gorm-jwt-mysql/repository"
	"golang.org/x/crypto/bcrypt"
)

// AuthService is a contract about some auth service can do
type AuthService interface {
	//VerifyCredential is verify user credential
	VerifyCredential(name string, password string) interface{}
	//CreateUser is insert user to db and return user entity to caller function
	CreateUser(user dto.RegisterDTORequest) entity.User
	FindByName(name string) entity.User
	IsDuplicateName(name string) bool
}

// Create a new authService with the given userRepository.
type authService struct {
	userRepository repository.UserRepository
}

// NewAuthService is creates a new instance of AuthService with the given userRepository.
func NewAuthService(userRepository repository.UserRepository) AuthService {
	return &authService{userRepository: userRepository}
}

// VerifyCredential is verify user credential and return user entity to caller function
func (s *authService) VerifyCredential(name string, password string) interface{} {
	//verify user credential and return user entity to caller function
	res := s.userRepository.VerifyCredential(name, password)

	//if res is user entity then return user entity to caller function
	if v, ok := res.(entity.User); ok {
		/*
			compare password with hashed password and return true if password is matched or return false if password is not matched
		*/
		comparedPassword := comparePassword(v.Password, []byte(password))
		if v.Name == name && comparedPassword {
			return res //return user entity to caller function
		}

		return false
	}
	return false //return false if res is not user entity

}

// CreateUser is insert user to db and return user entity to caller function
func (s *authService) CreateUser(user dto.RegisterDTORequest) entity.User {

	userToCreate := entity.User{} // create user entity

	/*
		fill user entity with data from dto request entity and return error if any error occur during mapping process or return nil if no error occur during mapping process and return user entity to caller function to use it
	*/
	err := smapping.FillStruct(&userToCreate, smapping.MapFields(&user))
	if err != nil {
		log.Fatalf("Failed map %v", err)
	}

	//insert user to db and return user entity to caller function
	res := s.userRepository.InsertUser(userToCreate)
	return res //return user entity to caller function

}

func (s *authService) FindByName(name string) entity.User {
	return s.userRepository.FindByName(name)
}

func (s *authService) IsDuplicateName(name string) bool {
	res := s.userRepository.IsDuplicateName(name)
	return !(res.Error == nil)
}

/*
comparePassword is compare password with hashed password and return true if password is matched or return false if password is not matched
*/
func comparePassword(hashedPwd string, plainPassword []byte) bool {

	//convert hashed password to byte array
	byteHash := []byte(hashedPwd)

	//compare password with hashed password
	err := bcrypt.CompareHashAndPassword(byteHash, plainPassword)
	if err != nil {
		log.Println(err)
		return false
	}
	/*
		return true if password is matched or return false if password is not matched
	*/
	return true

}
