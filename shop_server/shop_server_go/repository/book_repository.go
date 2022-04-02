package repository

import (
	"github.com/sumitroajiprabowo/gin-gorm-jwt-mysql/beans"
	"gorm.io/gorm"
)

type BookRepository interface {
	GetAll() []beans.Book                 // get all book from database
	GetByID(bookID uint64) beans.Book     // get book by bookID
	GetAllMyBook() []beans.Book           // get all book by userID
	Query() []beans.CategoryInfo          // get all book by userID
	CreateMyBook(b beans.Book) beans.Book // create book by userID
	UpdateMyBook(b beans.Book) beans.Book // update book by userID
	DeleteMyBook(b beans.Book)            // delete book by userID
}

// Create bookConnection struct to implement connection to database
type bookConnection struct {
	connection *gorm.DB // connection to database
}

// NewBookConnection method is used to create a new instance of bookConnection
func NewBookRepository(connection *gorm.DB) BookRepository {
	return &bookConnection{connection: connection}
}

// GetAll method is used to get all book from database
func (db *bookConnection) Query() []beans.CategoryInfo {
	var categoryInfos []beans.CategoryInfo                     // create variable categoryInfos to store all book
	db.connection.Preload("CategoryInfo").Find(&categoryInfos) // get all book and preload user from book
	return categoryInfos                                       // return all book
}

// GetAll method is used to get all book from database
func (db *bookConnection) GetAll() []beans.Book {
	var books []beans.Book                     // create variable books to store all book
	db.connection.Preload("User").Find(&books) // get all book and preload user from book
	return books                               // return all book
}

// GetAllMyBook method is used to get all book by userID
func (db *bookConnection) GetAllMyBook() []beans.Book {
	var books []beans.Book                     // create variable books to store all book
	db.connection.Preload("User").Find(&books) // get all book and preload user from book
	return books                               // return all book
}

// GetByID method is used to get book by bookID
func (db *bookConnection) GetByID(bookID uint64) beans.Book {
	var book beans.Book                               // create variable book
	db.connection.Preload("User").Find(&book, bookID) // get data book from bookID and preload user from book
	return book                                       // return book
}

// CreateMyBook method is used to create book by userID
func (db *bookConnection) CreateMyBook(b beans.Book) beans.Book {
	db.connection.Save(&b)                 // save insert book
	db.connection.Preload("User").Find(&b) // get data user from book
	return b                               // return book
}

// UpdateMyBook method is used to update book by userID
func (db *bookConnection) UpdateMyBook(b beans.Book) beans.Book {
	db.connection.Save(&b)                 // save update book
	db.connection.Preload("User").Find(&b) // get data user from book
	return b                               // return book
}

// DeleteMyBook method is used to delete book by userID
func (db *bookConnection) DeleteMyBook(b beans.Book) {
	db.connection.Delete(&b) // delete book
}
