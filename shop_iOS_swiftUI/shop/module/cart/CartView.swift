// 厦门大学计算机专业 | 前华为工程师
// 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
// 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
// 公众号：蓝不蓝编程

import SwiftUI
import SDWebImageSwiftUI

struct CartView: View {
    @StateObject private var cartViewModel = CartViewModel()
    @State var imageIndex: Int = 0
    
    var body: some View {
        NavigationView {
            VStack{
                List {
                    ForEach(cartViewModel.dataList.indices , id: \.self){ i in
                        NavigationLink(destination: GoodsView(goods:Goods())) {
                            CartItemView(cartItem: cartViewModel.dataList[i])
                        }
                    }
                }
            }
        }
        .onAppear(perform: {
            cartViewModel.queryData(categoryId: "1")
        })
    }
}

struct CartItemView: View {
    var cartItem: CartItem
    var body: some View {
        HStack{
            WebImage(url: URL(string: cartItem.squarePic ?? ""))
                .placeholder{Color.gray}
                .resizable()
                .scaledToFit()
                .frame(width: 100, height: 100)
            Text(cartItem.name).lineLimit(3)
        }
        
    }
}

struct CartView_Previews: PreviewProvider {
    static var previews: some View {
        CartView()
    }
}
