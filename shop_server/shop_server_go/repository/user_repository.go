package repository

import (
	"github.com/sumitroajiprabowo/gin-gorm-jwt-mysql/beans"
	"log"

	"golang.org/x/crypto/bcrypt"
	"gorm.io/gorm"
)

//UserRepository is contract what userRepository can do to db
type UserRepository interface {
	//InsertUser is insert user to db
	InsertUser(user beans.User) beans.User

	//UpdateUser is update user to db
	UpdateUser(user beans.User) beans.User

	//VerifyCredential is verify user credential
	VerifyCredential(name string, password string) interface{}

	IsDuplicateName(name string) (tx *gorm.DB)

	FindByName(name string) beans.User

	//ProfileUser is find user by id
	ProfileUser(userID int64) beans.User
}

//userConnection is a struct that implements connection to db with gorm
type userConnection struct {
	connection *gorm.DB //connection to db with gorm
}

/*
NewUserRepository is creates a new instance of UserRepository with gorm connection instance as parameter and return UserRepository interface instance to use
*/
func NewUserRepository(db *gorm.DB) UserRepository {
	return &userConnection{
		connection: db, //set connection to db
	}
}

// CreateUser is insert user to db and return user entity to caller function
func (db *userConnection) InsertUser(user beans.User) beans.User {
	user.Password = hashAndSalt([]byte(user.Password)) //hash password
	db.connection.Save(&user)                          //save user to db
	return user
}

// UpdateUser is update user to db and return user entity to caller function
func (db *userConnection) UpdateUser(user beans.User) beans.User {
	if user.Password != "" {
		user.Password = hashAndSalt([]byte(user.Password)) //hash password
	} else {
		var tempUser beans.User                //get user from db
		db.connection.Find(&tempUser, user.ID) //find user by id
		user.Password = tempUser.Password      //set password to user
	}
	db.connection.Save(&user) //save user to db
	return user
}

// VerifyCredential is verify user credential and return user entity to caller function if credential is correct or return nil if credential is incorrect
func (db *userConnection) VerifyCredential(name string, password string) interface{} {
	var user beans.User
	res := db.connection.Where("name = ?", name).Take(&user)
	if res.Error == nil {
		return user
	}
	return nil
}

func (db *userConnection) IsDuplicateName(name string) (tx *gorm.DB) {
	var user beans.User                                      //get user from db
	return db.connection.Where("name = ?", name).Take(&user) //find user by email
}

// FindByName is find user by email and return user entity to caller function
func (db *userConnection) FindByName(name string) beans.User {
	var user beans.User                               //get user from db
	db.connection.Where("name = ?", name).Take(&user) //find user by email
	return user                                       //return user
}

// ProfileUser is find user by id and return user entity to caller function
func (db *userConnection) ProfileUser(userID int64) beans.User {
	var user beans.User                                                      // get user from db
	db.connection.Preload("Books").Preload("Books.User").Find(&user, userID) //find user by id and preload books and user
	return user                                                              //return user
}

// hashAndSalt is hash password and return hashed password
func hashAndSalt(pwd []byte) string {
	hash, err := bcrypt.GenerateFromPassword(pwd, bcrypt.MinCost) //hash password
	if err != nil {
		log.Println(err)
		panic("Failed to hash a password") //panic if failed to hash password
	}
	return string(hash) //return hashed password
}
