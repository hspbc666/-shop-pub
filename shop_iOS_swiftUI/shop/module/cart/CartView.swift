// 厦门大学计算机专业 | 前华为工程师
// 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
// 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
// 公众号：蓝不蓝编程

import SwiftUI
import SDWebImageSwiftUI

struct CartView: View {
    @StateObject private var viewModel = CartViewModel()
    @State var imageIndex: Int = 0
    @State var _selectedCartIndexes: [Int] = []
    @State var _selectedSum = 0
    
    var body: some View {
        NavigationView {
            VStack{
                List {
                    ForEach(viewModel.dataList.indices , id: \.self){ i in
                        CartItemView(cartItem: $viewModel.dataList[i])
                    }
                }
                Spacer()
                HStack{
                    Spacer()
                    
                    Text("已选 (\(viewModel.dataList.filter(){return $0.isSelected == true}.count))")
                    Spacer()
                    Text("总计")
                    Text("￥\(_selectedSum)")
                    Spacer()
                    Button(action:{ print("我被点啦")}){
                        Text("结算").font(.headline).frame(minWidth: 150)
                    }
                    .padding(EdgeInsets.init(top: 5, leading: 0, bottom: 5, trailing: 0))
                    .foregroundColor(.white)
                    .background(Color.main_color)
                    .clipShape(RoundedRectangle(cornerRadius: 5))
                    Spacer()
                }
            }
        }.onAppear(perform: {
            viewModel.queryData()
        })
    }
}

struct CartItemView: View {
    @Binding var cartItem: CartItem
    var body: some View {
        HStack{
            Toggle("", isOn: $cartItem.isSelected)
                .toggleStyle(CheckBoxToggleStyle(shape: .square))
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
