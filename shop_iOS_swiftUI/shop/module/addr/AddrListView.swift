// 厦门大学计算机专业 | 前华为工程师
// 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
// 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
// 公众号：蓝不蓝编程

import SwiftUI
import SDWebImageSwiftUI

struct AddrListView: View {
    @StateObject private var viewModel = AddrListViewModel()
    @State var imageIndex: Int = 0
    @State var _selectedCartIndexes: [Int] = []
    @State var _selectedSum = 0
    
    var body: some View {
        NavigationView {
            VStack{
                List {
                    ForEach(viewModel.dataList.indices , id: \.self){ i in
                        NavigationLink(destination: Text("")) {
//                            CartItemView(cartItem: viewModel.dataList[i])
                        }
                    }
                }
            }
        }
        
    }
}


struct AddrListView_Previews: PreviewProvider {
    static var previews: some View {
        AddrListView()
    }
}
