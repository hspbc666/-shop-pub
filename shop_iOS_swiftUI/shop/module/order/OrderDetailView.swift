// 厦门大学计算机专业 | 前华为工程师
// 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
// 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
// 公众号：蓝不蓝编程

import SwiftUI
import SDWebImageSwiftUI

struct OrderDetailView: View {
    var orderInfo: OrderInfo
    @State private var viewModel = OrderViewModel()
    
    var body: some View {
        buildOrderItemView(orderInfo: orderInfo)
            .frame(maxWidth: .infinity,maxHeight: .infinity, alignment: .top)
            .background(Color(hex: 0xF4F4F4))
            
    }
    
    private func buildOrderItemView(orderInfo: OrderInfo) -> some View{
        return VStack{
            HStack{
                Text("X商自营").font(.title3)
                Spacer()
                Text("状态").foregroundColor(.gray)
            }
            
            buildOrderListView(list: orderInfo.list)
            
            HStack{
                Spacer()
                Text("共"+String(orderInfo.list.count)+"件商品").padding()
            }
            
            HStack{
                Spacer()
                Text("发票详情").padding(EdgeInsets.init(top: 5, leading: 10, bottom: 5, trailing: 10))
                    .background(RoundedRectangle(cornerRadius: 50).strokeBorder(Color.gray,lineWidth: 1))
                    .foregroundColor(Color(hex: 0x141414))
                Text("申请售后").padding(EdgeInsets.init(top: 5, leading: 10, bottom: 5, trailing: 10))
                    .background(RoundedRectangle(cornerRadius: 50).strokeBorder(Color.gray,lineWidth: 1))
                    .foregroundColor(Color(hex: 0x141414))
            }
        }.padding(EdgeInsets.init(top: 20, leading: 10, bottom: 10, trailing: 10))
            .frame(maxWidth: .infinity,alignment: .leading)
            .background(.white)
            .clipShape(RoundedRectangle(cornerRadius: 5))
            .padding()
    }
    private func buildOrderListView(list: [OrderDetail]) -> some View{
        return VStack{
            ForEach(list.indices , id: \.self){ j in
                let orderDetail = list[j]
                HStack{
                    WebImage(url: URL(string: orderDetail.squarePic ?? ""))
                        .placeholder{Color.gray}
                        .resizable()
                        .scaledToFit()
                        .frame(width: 50, height: 50)
                    VStack(alignment: .leading){
                        Text(orderDetail.name).padding(EdgeInsets.init(top: 0, leading: 0, bottom: 5, trailing: 0))
                        HStack{
                            Text("7天无理由退货")
                                .font(.system(size:12))
                                .foregroundColor(Color(hex: 0x677DA5))
                                .padding(EdgeInsets.init(top: 5, leading: 5, bottom: 5, trailing: 5))
                                .background(RoundedRectangle(cornerRadius: 5).foregroundColor(Color(hex: 0xEAF3FF)))
                            Text("不支持换货")
                                .font(.system(size:12))
                                .foregroundColor(Color(hex: 0x97999E))
                                .padding(EdgeInsets.init(top: 5, leading: 5, bottom: 5, trailing: 5))
                                .background(RoundedRectangle(cornerRadius: 5).foregroundColor(Color(hex: 0xF2F5F5)))
                        }
                    }
                    VStack{
                        Text("￥" + String(orderDetail.price/100))
                        Text("×" + String(orderDetail.quantity))
                    }
                }
            }
        }
    }
}


//struct OrderDetailView_Previews: PreviewProvider {
//    static var previews: some View {
//        OrderDetailView()
//    }
//}
