// 厦门大学计算机专业 | 前华为工程师
// 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
// 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
// 公众号：蓝不蓝编程

import SwiftUI
import SDWebImageSwiftUI

struct SelectAddrView: View {
    @State var selectedUserAddr: UserAddr
    @State private var viewModel = AddrViewModel()
    @State private var refreshViewModel = RefreshViewModel()
    
    var body: some View {
        VStack{
            List {
                ForEach(viewModel.dataList.indices , id: \.self){ i in
                    SelectAddrItemView(selectedUserAddr: $selectedUserAddr,
                                       viewModel: viewModel,
                                       refreshViewModel: refreshViewModel,
                                       userAddr: viewModel.dataList[i])
                }
            }
            NavigationLink(destination: AddAddrView(viewModel: viewModel, refreshViewModel: refreshViewModel)){
                Text("添加收货地址").font(.headline).frame(maxWidth:.infinity)
                    .padding(EdgeInsets.init(top: 10, leading: 0, bottom: 10, trailing: 0))
                    .foregroundColor(.white)
                    .background(Color.main_color)
                    .clipShape(RoundedRectangle(cornerRadius: 5))
                    .padding(EdgeInsets.init(top: 2, leading: 10, bottom: 5, trailing: 10))
            }
            
        }.onAppear(perform: {
            viewModel.queryData()
        })
    }
}

struct SelectAddrItemView: View {
    @Binding var selectedUserAddr: UserAddr
    var viewModel: AddrViewModel
    var refreshViewModel: RefreshViewModel
    var userAddr: UserAddr
    @State private var isShowingDetailView = false
    var body: some View {
        HStack{
            SimpleRadioButton(id: userAddr.id, selectedID: selectedUserAddr.id, callBack: self.radioButtonCallBack)
            VStack{
                HStack{
                    Text(userAddr.name)
                    Text(userAddr.phone).foregroundColor(Color.gray)
                }
                HStack{
                    if userAddr.defaultAddress {
                        Text("默认").foregroundColor(.white)
                            .frame(width:50, height: 30)
                            .background(RoundedRectangle(cornerRadius: 5)
                                            .foregroundColor(Color(hex: 0x8298bd)))
                    }
                    Text(userAddr.address)
                }
            }
        }
        
        HStack{
            Spacer()
            Text("编辑").frame(width:60, height: 30)
                .background(RoundedRectangle(cornerRadius: 50).strokeBorder(Color.gray,lineWidth: 1))
                .background(NavigationLink("", destination: EditAddrView(viewModel: viewModel, refreshViewModel: refreshViewModel, userAddr: userAddr)).opacity(0) )
        }
    }
    
    func radioButtonCallBack(id: String) {
        //        self.selectedID = id
    }
}

//struct SelectAddrView_Previews: PreviewProvider {
//    static var previews: some View {
//        SelectAddrView()
//    }
//}
