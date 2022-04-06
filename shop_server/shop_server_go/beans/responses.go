package beans

type CategoryInfo struct {
	Id   string `json:"id"`
	Name string `json:"name"`
}

func (CategoryInfo) TableName() string { return "category" }

type Goods struct {
	Id    string `json:"id"`
	Name  string `json:"name"`
	Price uint64 `json:"price"`
}

func (Goods) TableName() string { return "goods" }

type Response struct {
	Code int         `json:"code"`
	Msg  string      `json:"msg"`
	Data interface{} `json:"data"`
}

type EmptyObject struct {
}

func SuccessResponse(code int, message string, data interface{}) Response {
	return Response{
		Code: code,
		Msg:  message,
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
