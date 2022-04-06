package services

import (
	"fmt"
	"github.com/sumitroajiprabowo/gin-gorm-jwt-mysql/beans"
	"log"

	"github.com/mashingan/smapping"
	"github.com/sumitroajiprabowo/gin-gorm-jwt-mysql/repository"
)

type BookService interface {
	CreateMyBook(b beans.BookCreateDTORequest) beans.Book // Create a new book
	UpdateMyBook(b beans.BookUpdateDTORequest) beans.Book // Update a book
	DeleteMyBook(b beans.Book)                            // Delete a book
	GetAll() []beans.Book                                 // Get all book
	QueryCategory() []beans.CategoryInfo
	QueryGoods() []beans.Goods
	GetByID(bookID uint64) beans.Book                      // Get a book by bookID
	GetAllMyBook() []beans.Book                            // Get all book by userID
	IsAllowedActionBook(userID string, bookID uint64) bool // Check userID is allowed to access bookID
}

// Create a bookService struct to implement BookService interface
type bookService struct {
	bookRepository repository.BookRepository
}

func (s *bookService) QueryCategory() []beans.CategoryInfo {
	return s.bookRepository.QueryCategory()
}

func (s *bookService) QueryGoods() []beans.Goods {
	return s.bookRepository.QueryGoods()
}

// NewBookService method is used to create a new instance of bookService
func NewBookService(bookRepo repository.BookRepository) BookService {
	return &bookService{bookRepository: bookRepo}
}

// GetAll method is used to get all book
func (s *bookService) GetAll() []beans.Book {
	return s.bookRepository.GetAll()
}

// GetByID method is used to get a book by bookID
func (s *bookService) GetByID(bookID uint64) beans.Book {
	return s.bookRepository.GetByID(bookID)
}

// GetAllMyBook method is used to get all book by userID
func (s *bookService) GetAllMyBook() []beans.Book {
	return s.bookRepository.GetAllMyBook()
}

// CreateMyBook method is used to create a book by userID
func (s *bookService) CreateMyBook(b beans.BookCreateDTORequest) beans.Book {
	book := beans.Book{}                                      // book is a new instance of Book
	err := smapping.FillStruct(&book, smapping.MapFields(&b)) // Fill the book with the book data
	if err != nil {
		log.Fatalf("Failed to map fields %v: ", err)
	}
	result := s.bookRepository.CreateMyBook(book) // Create the book
	return result
}

// UpdateMyBook method is used to update a book by userID
func (s *bookService) UpdateMyBook(b beans.BookUpdateDTORequest) beans.Book {
	book := beans.Book{}                                      // book is a new instance of Book
	err := smapping.FillStruct(&book, smapping.MapFields(&b)) // Fill the book with the book data
	if err != nil {
		log.Fatalf("Failed to map fields %v: ", err)
	}
	result := s.bookRepository.UpdateMyBook(book) // Update the book
	return result
}

// DeleteMyBook method is used to delete a book by userID
func (s *bookService) DeleteMyBook(b beans.Book) {
	s.bookRepository.DeleteMyBook(b) // delete book
}

// IsAllowedActionBook method is used to check userID is allowed to access bookID or not by userID
func (s *bookService) IsAllowedActionBook(userID string, bookID uint64) bool {
	b := s.bookRepository.GetByID(bookID) // Get a book by bookID
	id := fmt.Sprintf("%v", b.UserID)     // Get userID from book
	return userID == id                   // Check userID is allowed to access bookID
}
