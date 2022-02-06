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
    case register(params: [String:Any])
    case queryGoodsByCategory(categoryId: String)
    case searchGoods(keyword: String)
    case queryCart
    case addData(params: [String:Any])
    case modifyData(params: [String:Any])
    case deleteData(params:String)
}

extension LblAPI: TargetType {
    public var baseURL: URL {
//        return URL(string: "http://192.168.31.10:8080/")!
                return URL(string: "http://10.10.10.200:8080/")!
    }
    
    var path: String {
        switch self {
        case .login: return "user/login"
        case .register: return "user/register"
        case .queryGoodsByCategory(let categoryId): return "shop/goods/category/"+categoryId
        case .searchGoods(let keyword): return "shop/goods/search/"+keyword
        case .queryCart: return "shop/cart/list"
        case .addData: return "note/add"
        case .modifyData: return "note/modify"
        case .deleteData(let params): return "note/del/"+params
        }
    }
    
    var method: Moya.Method {
        switch self {
        case .login, .register, .addData, .modifyData:
            return .post
        case .queryGoodsByCategory, .searchGoods, .deleteData, .queryCart:
            return .get
        }
    }
    
    var task: Task {
        switch self {
        case .login(let params),.register(let params),.addData(let params),.modifyData(let params):
            return .requestParameters(parameters: params, encoding: JSONEncoding.default)
        case .queryGoodsByCategory, .searchGoods, .queryCart, .deleteData:
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
