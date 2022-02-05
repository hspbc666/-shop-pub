// 厦门大学计算机专业 | 前华为工程师
// 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
// 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
// 公众号：蓝不蓝编程
import SwiftUI

struct LoginView : View {
    var refreshViewModel: RefreshViewModel
    @Binding var isLoginViewPresented: Bool
    @State var name: String = "lbl"
    @State var password: String = "1"
    @State var error: String = ""
    @State var showPwd = false
    
    var isCanLogin: Bool {
        name.count > 0 &&
        password.count > 0
    }
    
    var body: some View {
        VStack {
            Text(error).foregroundColor(.red)
            HStack {
                Image(systemName: "person")
                TextField("请输入用户名", text: $name)
            }
            Divider()
            HStack {
                Image(systemName: "lock")
                if showPwd {
                    TextField("请输入密码", text: $password)
                } else {
                    SecureField("请输入密码", text: $password)
                }
                Button(action: {
                    self.showPwd.toggle()
                }) {
                    Image(systemName: self.showPwd ? "eye" : "eye.slash")
                }
                
            }
            Divider()
            HStack{
                Button(action: {
                    error = ""
                    LoginViewModel.shared.login(name: name , password: password){isSuccess,msg in
                        if(isSuccess){
                            isLoginViewPresented = false
                            refreshViewModel.shouldRefresh = true
                        }else{
                            error = msg
                        }
                    }
                }) {
                        Text("登录").foregroundColor(.white)
                    }
                    .frame(width: 100, height: 45, alignment: .center)
                    .background(isCanLogin ? Color.blue: Color.gray)
                    .cornerRadius(10)
                    .disabled(!isCanLogin)
                
                Button(action: {
                    error = ""
                    LoginViewModel.shared.register(name: name , password: password){isSuccess,msg in
                        if(isSuccess){
                            isLoginViewPresented = false
                            refreshViewModel.shouldRefresh = true
                        }else{
                            error = msg
                        }
                    }
                }) {
                        Text("注册").foregroundColor(.white)
                    }
                    .frame(width: 100, height: 45, alignment: .center)
                    .background(isCanLogin ? Color.blue: Color.gray)
                    .cornerRadius(10)
                    .disabled(!isCanLogin)
            }
            
            Spacer()
        }
        .padding(.top, 100)
        .padding(.leading)
        .padding(.trailing)
    }
}

//#if DEBUG
//struct LoginView_Previews : PreviewProvider {
//    @State private var isLoginViewPresented: Bool = true
//    static var previews: some View {
//        LoginView(isAddPresented : $isLoginViewPresented)
//    }
//}
//#endif

