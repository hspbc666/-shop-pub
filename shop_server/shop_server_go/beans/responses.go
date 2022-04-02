package beans

import "strings"

type CategoryInfo struct {
	Id   string `json:"id"`
	Name string `json:"name"`
}

func (CategoryInfo) TableName() string {
	return "category"
}

type Response struct {
	Code   int         `json:"code"`
	Msg    string      `json:"msg"`
	Errors interface{} `json:"errors"`
	Data   interface{} `json:"data"`
}

type EmptyObject struct {
}

func SuccessResponse(code int, message string, data interface{}) Response {
	return Response{
		Code:   code,
		Msg:    message,
		Errors: nil,
		Data:   data,
	}
}

func ErrorsResponse(code int, message string, err string, data interface{}) Response {
	splittedError := strings.Split(err, "\n")
	return Response{
		Code:   code,
		Msg:    message,
		Errors: splittedError,
		Data:   data,
	}
}
