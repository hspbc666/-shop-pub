// 厦门大学计算机专业 | 前华为工程师
// 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
// 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
// 公众号：蓝不蓝编程

import SwiftUI
import SDWebImageSwiftUI

struct AddrListView: View {
    @StateObject private var viewModel = AddrListViewModel()
    
    var body: some View {
        NavigationView {
            VStack{
                List {
                    ForEach(viewModel.dataList.indices , id: \.self){ i in
                        NavigationLink(destination: Text("")) {
                            AddrItemView(userAddr: viewModel.dataList[i])
                        }
                    }
                }
            }
        }
        
    }
}

struct AddrItemView: View {
    var userAddr: UserAddr
    var body: some View {
        HStack{
            Text(userAddr.name).lineLimit(3)
        }
        
    }
}



struct AddrListView_Previews: PreviewProvider {
    static var previews: some View {
        AddrListView()
    }
}
