// 厦门大学计算机专业 | 前华为工程师
// 分享编程技术，没啥深度，但看得懂，适合初学者。
// Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
// 公众号：蓝不蓝编程

import Foundation
import Moya
import HandyJSON
import SwiftyJSON

let LblProvider = MoyaProvider<LblAPI>()


enum LblAPI {
    case login(params: [String:Any])
    case register(params: [String:Any])
    case queryCategory
    case queryGoodsByCategory(categoryId: String)
    case searchGoods(keyword: String)
    case queryCart
    case addToCart(goodsId: String)
    case queryAddress
    case queryDefaultAddress
    case deleteAddress(userAddrId: String)
    case addAddress(params: UserAddr)
    case modifyAddress(params: UserAddr)
    case queryOrderByStatus(orderStatus: Int)
    case queryOrder(orderId: String)
    case deleteOrder(orderId: String)
    case createOrder(params: CreateOrderReq)
}

extension LblAPI: TargetType {
    public var baseURL: URL {
        return URL(string: "http://192.168.31.10:8080/")!
//              return URL(string: "http://192.168.1.108:8080/")!
    }
    
    var path: String {
        switch self {
        case .login: return "user/login"
        case .register: return "user/register"
        case .queryCategory: return "shop/category"
        case .queryGoodsByCategory(let categoryId): return "shop/goods/category/"+categoryId
        case .searchGoods(let keyword): return "shop/goods/search/"+keyword
        case .queryCart: return "shop/cart/list"
        case .addToCart(let goodsId): return "shop/cart/add/"+goodsId
        case .queryAddress: return "shop/addr/query"
        case .queryDefaultAddress: return "shop/addr/query_default"
        case .deleteAddress(let userAddrId): return "shop/addr/del/"+userAddrId
        case .addAddress: return "shop/addr/add"
        case .modifyAddress: return "shop/addr/modify"
        case .queryOrderByStatus(let orderStatus): return "shop/order/queryByStatus/\(orderStatus)"
        case .queryOrder(let orderId): return "shop/order/query/\(orderId)"
        case .deleteOrder(let orderId): return "shop/order/del/\(orderId)"
        case .createOrder: return "shop/order/create"
        }
    }
    
    var method: Moya.Method {
        switch self {
        case .login, .register, .addAddress, .modifyAddress, .createOrder:
            return .post
        case .queryCategory, .queryGoodsByCategory, .searchGoods, .queryCart, .queryAddress, .queryDefaultAddress, .addToCart, .deleteAddress, .queryOrderByStatus, .queryOrder, .deleteOrder:
            return .get
        }
    }
    
    var task: Task {
        switch self {
        case .addAddress(let params), .modifyAddress(let params), .createOrder(let params):
            return .requestParameters(parameters: params.toJSON() ?? ["":""], encoding: JSONEncoding.default)
        case .login(let params), .register(let params):
            return .requestParameters(parameters: params, encoding: JSONEncoding.default)
        case .queryCategory, .queryGoodsByCategory, .searchGoods, .queryCart, .queryAddress, .queryDefaultAddress, .addToCart, .deleteAddress, .queryOrderByStatus, .queryOrder, .deleteOrder:
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
