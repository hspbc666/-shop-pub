package beans

type CategoryInfo struct {
	Id   string `json:"id"`
	Name string `json:"name"`
}

func (CategoryInfo) TableName() string { return "category" }

type GoodsCategory struct {
	ID         string `gorm:"primary_key" json:"id"`
	CategoryId string `gorm:"type:varchar(200)" json:"categoryId"`
	GoodsId    string `gorm:"type:varchar(200)" json:"goodsId"`
	Goods      *Goods `gorm:"foreignkey:GoodsId;references:Id;constraint:onUpdate:CASCADE,onDelete:CASCADE"`
}

func (GoodsCategory) TableName() string { return "goods_category" }

type Goods struct {
	Id           string `gorm:"primary_key" json:"id"`
	Name         string `gorm:"type:varchar(200)" json:"name"`
	Price        int64  `gorm:"type:int(10)" json:"price"`
	LongPic      string `gorm:"type:varchar(200)" json:"longPic"`
	SquarePic    string `gorm:"type:varchar(200)" json:"squarePic"`
	DescPic      string `gorm:"type:varchar(200)" json:"descPic"`
	OriginalLink string `gorm:"type:varchar(200)" json:"originalLink"`
}

func (Goods) TableName() string { return "goods" }

type Response struct {
	Code int         `json:"code"`
	Msg  string      `json:"msg"`
	Data interface{} `json:"data"`
}

type EmptyObject struct {
}

func SuccessResponse(code int, data interface{}) Response {
	return Response{
		Code: code,
		Msg:  "",
		Data: data,
	}
}

func ErrorsResponse(code int, message string, data interface{}) Response {
	return Response{
		Code: code,
		Msg:  message,
		Data: data,
	}
}
