// 厦门大学计算机专业 | 前华为工程师
// 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
// 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
// 公众号：蓝不蓝编程

import SwiftUI
import SDWebImageSwiftUI

struct CategoryView: View {
    var tabs: [LblTab] = [LblTab(id: "1",name: "测试11"),LblTab(id: "2",name: "测试2"),LblTab(id: "3",name: "测试3")]
    @State var selectedIndex = 0
    
    var body: some View {
        VStack{
            ScrollView(.horizontal) {
                HStack {
                    ForEach(0..<tabs.count) { i in
                        TabItemView(i)
                    }
                }
            }.padding(EdgeInsets.init(top: 0, leading: 10, bottom: 0, trailing: 10))
            LblTabPageView(selectedIndex: $selectedIndex, tabs: tabs)
        }        .navigationBarTitle(Text("X商城"), displayMode: .inline)
            .navigationBarItems(trailing:NavigationLink(destination: SearchView()) {
                Image(systemName: "magnifyingglass")
            })
    
    }
    
    fileprivate func TabItemView(_ i: Int) -> some View {
        return VStack{
            Text(tabs[i].name)
                .foregroundColor(selectedIndex == i ? Color.main_color : .gray)
                .padding(EdgeInsets.init(top: 5, leading: 10, bottom: 5, trailing: 10))
                .onTapGesture(perform: {
                    selectedIndex = i
                })
            Divider().frame(height: 2).background(selectedIndex == i ? Color.main_color : .white)
        }
    }
}

struct LblTab{
    var id: String = ""
    var name: String = ""
    
    init(id:String, name:String)
    {
        self.id = id
        self.name = name
    }
}

struct LblTabPageView: View {
    @Binding var selectedIndex: Int
    let tabs: [LblTab]
    
    @StateObject private var viewModel = CategoryViewModel()
    
    var body: some View {
        List {
            ForEach(viewModel.dataList.indices , id: \.self){ i in
                NavigationLink(destination: GoodsView(goods:viewModel.dataList[i])) {
                    GoodsItemView(goods: viewModel.dataList[i])
                }
            }
        }.onChange(of: selectedIndex) {
            viewModel.queryData(categoryId: tabs[$0].id)
        }.onAppear(perform: {
            print("111111")
            viewModel.queryData(categoryId: tabs[selectedIndex].id)
        })
    }
}


struct CategoryView_Previews: PreviewProvider {
    static var previews: some View {
        CategoryView()
    }
}
