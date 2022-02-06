// 厦门大学计算机专业 | 前华为工程师
// 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
// 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
// 公众号：蓝不蓝编程

import SwiftUI
import SDWebImageSwiftUI

struct CategoryView: View {
    @StateObject private var viewModel = CategoryViewModel()
    @State var imageIndex: Int = 0
    
    var body: some View {
        NavigationView {
            VStack{
                List {
                    ForEach(viewModel.dataList.indices , id: \.self){ i in
                        NavigationLink(destination: GoodsView(goods:viewModel.dataList[i])) {
                            GoodsItemView(goods: viewModel.dataList[i])
                        }
                    }
                }
            }
            .navigationBarTitle(Text("X商城"), displayMode: .inline)
            .navigationBarItems(trailing:NavigationLink(destination: SearchView()) {
                Image(systemName: "magnifyingglass")
            })
            .onAppear(perform: {
                viewModel.queryData(categoryId: "1")
            })
        }
    }
}

struct CategoryView_Previews: PreviewProvider {
    static var previews: some View {
        CategoryView()
    }
}
