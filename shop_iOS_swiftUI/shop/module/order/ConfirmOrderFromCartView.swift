// 厦门大学计算机专业 | 前华为工程师
// 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
// 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
// 公众号：蓝不蓝编程

import SwiftUI
import SDWebImageSwiftUI

struct ConfirmOrderFromCartView: View {
    var costSum: Int
    var cartItems: [CartItem]
    @State var shouldShowNextPage = false
    @StateObject private var viewModel = ConfirmOrderViewModel()
    var body: some View {
        VStack{
            buildAddrInfoView()
            buildGoodsInfoView()
            buildDeliveryInfoView()
            buildFeeInfoView()
            Spacer()
            Divider()
            buildBottomView()
        }.background(Color(hex: 0xF4F4F4))
            .onAppear(perform: {
                viewModel.queryData()
            })
    }
    
    func buildGoodsInfoView() -> some View{
        HStack{
            if !cartItems.isEmpty {
                WebImage(url: URL(string: cartItems[0].squarePic ?? ""))
                    .placeholder{Color.gray}
                    .resizable()
                    .scaledToFit()
                    .frame(width: 100, height: 100)
                Spacer()
                Text("共\(cartItems.count)种").foregroundColor(.gray)
                Image(systemName: "chevron.right").foregroundColor(Color(hex: 0x595D63))
            }
        }.padding().background(.white).clipShape(RoundedRectangle(cornerRadius: 5)).padding()
        
    }
    
    func buildDeliveryInfoView() -> some View{
        HStack{
            Text("配送").foregroundColor(.gray).padding()
            VStack(alignment: .leading){
                Text("顺丰速运").font(.title3)
                Text("1个包裹，预计明天送达").foregroundColor(.gray)
            }
            Spacer()
            Image(systemName: "chevron.right").foregroundColor(Color(hex: 0x595D63))
        }.padding().background(.white).clipShape(RoundedRectangle(cornerRadius: 5)).padding()
    }
    
    func buildFeeInfoView() -> some View{
        return VStack{
            HStack{
                Text("商品金额").foregroundColor(.gray)
                Spacer()
                Text("￥\(costSum)")
            }.padding()
            HStack{
                Text("总运费").foregroundColor(.gray)
                Spacer()
                Text("满XX元 免邮")
            }.padding()
            HStack{
                Text("优惠券").foregroundColor(.gray)
                Spacer()
                Text("无可用券")
            }.padding()
        }.padding().background(.white).clipShape(RoundedRectangle(cornerRadius: 5)).padding()
    }
    
    func buildAddrInfoView() -> some View{
        return HStack{
            if let userAddr = viewModel.userAddr {
                HStack{
                    Image(systemName: "location.circle.fill")
                    VStack{
                        Text(userAddr.name)
                        Text(userAddr.address)
                    }
                    Spacer()
                    Image(systemName: "chevron.right").foregroundColor(Color(hex: 0x595D63))
                }
            }else{
                Text("+ 添加收货地址")
            }
        }.padding().background(.white).clipShape(RoundedRectangle(cornerRadius: 5)).padding()
    }
    func buildBottomView() -> some View{
        return HStack{
            Spacer()
            Text("实付款")
            Text("￥\(costSum)").foregroundColor(Color.main_color)
            Spacer()
            buildSubmitBtn()
            Spacer()
        } .padding(EdgeInsets.init(top: 0, leading: 0, bottom: 5, trailing: 0))
    }

    func buildSubmitBtn() -> some View{
        NavigationLink(destination: OrderDetailView(orderId: viewModel.createdOrderId), isActive: $shouldShowNextPage) {
                Button(action: {
                    viewModel.createOrder(cartItems: cartItems) { isSuccess,orderId,msg in
                        if(isSuccess){
                             self.shouldShowNextPage = true
                        }
                    }
                }) {
                    Text("提交订单").font(.headline).frame(minWidth: 150)
                            .padding(EdgeInsets.init(top: 5, leading: 0, bottom: 5, trailing: 0))
                            .foregroundColor(.white)
                            .background(Color.main_color)
                            .clipShape(RoundedRectangle(cornerRadius: 5))
                    }
                }
    }

}

//struct OrderFromCartView_Previews: PreviewProvider {
//    static var previews: some View {
//        OrderFromCartView()
//    }
//}
