package repository

import (
	"github.com/sumitroajiprabowo/gin-gorm-jwt-mysql/beans"
	"gorm.io/gorm"
)

type BookRepository interface {
	GetAll() []beans.Book                                 // get all book from database
	GetByID(bookID uint64) beans.Book                     // get book by bookID
	GetAllMyBook() []beans.Book                           // get all book by userID
	QueryCategory() []beans.CategoryInfo                  // get all book by userID
	QueryGoods(goodsId string) beans.Goods                // get all book by userID
	QueryGoodsByCategory(categoryId string) []beans.Goods // get all book by userID
	CreateMyBook(b beans.Book) beans.Book                 // create book by userID
	UpdateMyBook(b beans.Book) beans.Book                 // update book by userID
	DeleteMyBook(b beans.Book)                            // delete book by userID
}

type bookConnection struct {
	connection *gorm.DB // connection to database
}

func NewBookRepository(connection *gorm.DB) BookRepository {
	return &bookConnection{connection: connection}
}

func (db *bookConnection) QueryCategory() []beans.CategoryInfo {
	var categoryInfos []beans.CategoryInfo
	db.connection.Table("CategoryInfo").Find(&categoryInfos)
	return categoryInfos
}

func (db *bookConnection) QueryGoodsByCategory(categoryId string) []beans.Goods {
	var goodsList []beans.Goods
	db.connection.Table("GoodsCategory").Find(&goodsList, "CategoryId = ?", categoryId)
	return goodsList
}

func (db *bookConnection) QueryGoods(goodsId string) beans.Goods {
	var goods beans.Goods
	db.connection.Table("Goods").Find(&goods, goodsId)
	return goods
}

// GetAll method is used to get all book from database
func (db *bookConnection) GetAll() []beans.Book {
	var books []beans.Book                   // create variable books to store all book
	db.connection.Table("User").Find(&books) // get all book and Table user from book
	return books                             // return all book
}

// GetAllMyBook method is used to get all book by userID
func (db *bookConnection) GetAllMyBook() []beans.Book {
	var books []beans.Book                   // create variable books to store all book
	db.connection.Table("User").Find(&books) // get all book and Table user from book
	return books                             // return all book
}

// GetByID method is used to get book by bookID
func (db *bookConnection) GetByID(bookID uint64) beans.Book {
	var book beans.Book                             // create variable book
	db.connection.Table("User").Find(&book, bookID) // get data book from bookID and Table user from book
	return book                                     // return book
}

// CreateMyBook method is used to create book by userID
func (db *bookConnection) CreateMyBook(b beans.Book) beans.Book {
	db.connection.Save(&b)               // save insert book
	db.connection.Table("User").Find(&b) // get data user from book
	return b                             // return book
}

// UpdateMyBook method is used to update book by userID
func (db *bookConnection) UpdateMyBook(b beans.Book) beans.Book {
	db.connection.Save(&b)               // save update book
	db.connection.Table("User").Find(&b) // get data user from book
	return b                             // return book
}

// DeleteMyBook method is used to delete book by userID
func (db *bookConnection) DeleteMyBook(b beans.Book) {
	db.connection.Delete(&b) // delete book
}
