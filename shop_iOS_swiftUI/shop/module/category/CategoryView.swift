// 厦门大学计算机专业 | 前华为工程师
// 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
// 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
// 公众号：蓝不蓝编程

import SwiftUI
import SDWebImageSwiftUI

struct CategoryView: View {
    @StateObject private var viewModel = CategoryViewModel()
    @State var selectedIndex = 0
    
    var body: some View {
        VStack{
            if viewModel.categoryList.count > 0 {
                ScrollView(.horizontal) {
                    HStack {
                        ForEach(0..<viewModel.categoryList.count) { i in
                            TabItemView(i)
                        }
                    }
                }.padding(EdgeInsets.init(top: 0, leading: 10, bottom: 0, trailing: 10))
                CategoryPageView(viewModel: viewModel, selectedIndex: $selectedIndex)
                    .padding(EdgeInsets.init(top: 0, leading: 0, bottom: 1, trailing: 0))
            }
        }.navigationBarTitle(Text("X商城"), displayMode: .inline)
            .navigationBarItems(trailing:NavigationLink(destination: SearchView()) {
                Image(systemName: "magnifyingglass")
            })
            .onAppear(perform: {
                viewModel.