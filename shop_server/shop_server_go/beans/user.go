package beans

// Create User struct representing the user table in the database
type User struct {
	ID       uint64 `gorm:"primary_key;auto_increment" json:"id"` // Primary key, auto-increment id with json tag id for json marshalling
	Name     string `gorm:"type:varchar(255)" json:"name"`        // Data type varchar with json tag name for json marshalling
	Password string `gorm:"->;<-;not null" json:"-"`              // TablesPassword field with json tag password for json marshalling
	//Books    *[]Book `json:"books,omitempty"`
}

func (User) TableName() string {
	return "sys_user"
}
