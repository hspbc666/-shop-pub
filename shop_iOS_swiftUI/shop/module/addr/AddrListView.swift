// 厦门大学计算机专业 | 前华为工程师
// 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
// 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
// 公众号：蓝不蓝编程

import SwiftUI
import SDWebImageSwiftUI

struct AddrListView: View {
    @StateObject private var viewModel = AddrListViewModel()
    @State private var isLoginViewPresented: Bool = false
    
    var body: some View {
        VStack{
            List {
                ForEach(viewModel.dataList.indices , id: \.self){ i in
                    AddrItemView(userAddr: viewModel.dataList[i],isLoginViewPresented: $isLoginViewPresented)
                }
            }
        }.onAppear(perform: {
            viewModel.queryData()
        }).sheet(isPresented: $isLoginViewPresented, content: {
            Text("dddddddddddddddddddddddddddd")
        })
    }
}

struct AddrItemView: View {
    var userAddr: UserAddr
    @Binding var isLoginViewPresented: Bool
    var body: some View {
        VStack{
            HStack{
                Text(userAddr.name)
                Text(userAddr.phone).foregroundColor(Color.gray)
                if userAddr.defaultAddress {
                    Text("默认").foregroundColor(.white)
                        .frame(width:50, height: 30)
                        .background(RoundedRectangle(cornerRadius: 5)
                                        .foregroundColor(Color(hex: 0x8298bd)))
                }
               
                Spacer()
            }
            HStack{
                Text(userAddr.address)
                Spacer()
            }
            HStack{
                Spacer()
                Button(action: {
                    isLoginViewPresented = true
                }) {
                    Text("删除").frame(width:60, height: 30)
                        .background(RoundedRectangle(cornerRadius: 50).strokeBorder(Color.gray,lineWidth: 1))
                }
                Button(action: {
                    isLoginViewPresented = true
                }) {
                    Text("编辑").frame(width:60, height: 30)
                        .background(RoundedRectangle(cornerRadius: 50).strokeBorder(Color.gray,lineWidth: 1))
                }
            }.padding(EdgeInsets.init(top: 0, leading: 0, bottom: 5, trailing: 0))
        }
    }
}



struct AddrListView_Previews: PreviewProvider {
    static var previews: some View {
        AddrListView()
    }
}
