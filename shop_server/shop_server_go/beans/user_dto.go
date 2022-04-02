package beans

// Create User Update DTO Request Struct when user update profile
type UserUpdateDTORequest struct {
	ID       uint64 `json:"id" form:"id"`
	Name     string `json:"name" form:"name" binding:"required"`
	Password string `json:"password,omitempty" form:"password,omitempty"`
}
