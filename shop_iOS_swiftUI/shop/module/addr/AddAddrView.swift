// 厦门大学计算机专业 | 前华为工程师
// 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
// 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
// 公众号：蓝不蓝编程

import SwiftUI

struct AddAddrView: View {
    @EnvironmentObject  var viewModel: AddrListViewModel
    var refreshViewModel: RefreshViewModel
    @Environment(\.presentationMode) var presentationMode: Binding<PresentationMode>
    
    @State var name: String = ""
    @State var phone: String = ""
    @State var region: String = ""
    @State var address: String = ""
    @State var addrType: Int = 0
    @State var defaultAddress = true
    
    var addrTypeRadios: [RadioOption<Int>] = [
        RadioOption(label: "家庭", value: 1),
        RadioOption(label: "公司", value: 2),
        RadioOption(label: "其他", value: 3)
    ]
    
    var body: some View {
        VStack{
            buildAppInfo()
            Spacer()
            buildBottomBtn()
        }
        .frame(maxWidth: .infinity,maxHeight: .infinity, alignment: .top)
        .background(Color(hex: 0xF4F4F4))
    }
    
    fileprivate func buildAppInfo() -> some View {
        return VStack{
            HStack{
                Text("收货人")
                Spacer()
                TextField("请输入收货人真实姓名", text: $name)
            }
            Divider()
            HStack{
                Text("手机号")
                Spacer()
                TextField("请输入收货人手机号", text: $phone)
            }
            Divider()
            HStack{
                Text("所在地区")
                Spacer()
                TextField("所在地区", text: $region)
            }
            Divider()
            HStack{
                Text("详细地址")
                Spacer()
                TextField("请输入详细地址", text: $address)
            }
            Divider()
            HStack{
                Text("地址类型")
                Spacer()
                RadioGroup(options: addrTypeRadios, value: $addrType)
                
            }
            HStack{
                Toggle(isOn: $defaultAddress) {
                    Text("设为默认地址")
                }
            }
        }
        .padding()
        .background(.white)
    }
    
    fileprivate func buildBottomBtn() -> some View {
        return Button(action:{ addAddress() }){
            Text("确定").font(.headline).frame(maxWidth:.infinity)
        }
        .padding(EdgeInsets.init(top: 10, leading: 0, bottom: 10, trailing: 0))
        .foregroundColor(.white)
        .background(Color.main_color)
        .clipShape(RoundedRectangle(cornerRadius: 5))
        .padding(EdgeInsets.init(top: 2, leading: 10, bottom: 5, trailing: 10))
    }
    
    func addAddress() {
        var userAddr = UserAddr()
        userAddr.name = name
        userAddr.phone = phone
        userAddr.region = region
        userAddr.address = address
        userAddr.addrType = addrType
        userAddr.defaultAddress = defaultAddress
        viewModel.addAddress(userAddr: userAddr){isSuccess,msg in
            if(isSuccess){
                refreshViewModel.shouldRefresh = true
                goBack()
            }else{
                // error = msg
            }
        }
    }
    
    private func goBack(){
        self.presentationMode.wrappedValue.dismiss()
    }
}


//struct AddAddrView_Previews: PreviewProvider {
//    static var previews: some View {
//        AddAddrView()
//    }
//}
