// 厦门大学计算机专业 | 前华为工程师
// 分享编程技术，没啥深度，但看得懂，适合初学者。
// Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
// 公众号：蓝不蓝编程

import Foundation
import Moya
import HandyJSON


let LblProvider = MoyaProvider<LblAPI>()


enum LblAPI {
    case login(params: [String:Any])
    case queryData
    case addData(params: [String:Any])
    case modifyData(params: [String:Any])
    case deleteData(params:String)
}

extension LblAPI: TargetType {
    public var baseURL: URL {
        return URL(string: "http://192.168.31.10:8080/")!
    }
    
    var path: String {
        switch self {
        case .login: return "api/login"
        case .queryData: return "note/api/list"
        case .addData: return "note/api/add"
        case .modifyData: return "note/api/modify"
        case .deleteData(let params): return "note/api/del/"+params
        }
    }
    
    var method: Moya.Method {
        switch self {
        case .login,.addData,.modifyData:
            return .post
        case .queryData,.deleteData:
            return .get
        }
    }
    
    var task: Task {
        switch self {
        case .login(let params),.addData(let params),.modifyData(let params):
            return .requestParameters(parameters: params, encoding: JSONEncoding.default)
        case .queryData,.deleteData:
            return .requestPlain
        }
    }
    
    
    var sampleData: Data { return "".data(using: String.Encoding.utf8)! }
    var headers: [String : String]? {
        let userInfo = LoginViewModel.shared.userInfo
        var headerDict = ["Content-Type":"application/json;charset=utf-8"]
        if(userInfo != nil){
            headerDict["Authorization"] = "Bearer "+userInfo!.token
        }
        return headerDict
    }
}
