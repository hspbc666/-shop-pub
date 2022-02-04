import SwiftUI

struct LoginView : View {
    var refreshViewModel: RefreshViewModel
    @Binding var isLoginViewPresented: Bool
    @StateObject private var lblViewModel = LblViewModel()
    @State var name: String = "lbl"
    @State var password: String = "1"
    @State var showPwd = false
    
    var isCanLogin: Bool {
        name.count > 0 &&
        password.count > 0
    }
    
    var body: some View {
        VStack {
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
            Button(action: {
                isLoginViewPresented = false
                LoginViewModel.shared.login(name: name , password: password){
                    refreshViewModel.shouldRefresh = true
                }
            }) {
                    Text("登录").foregroundColor(.white)
                }
                .frame(width: 100, height: 45, alignment: .center)
                .background(isCanLogin ? Color.blue: Color.gray)
                .cornerRadius(10)
                .disabled(!isCanLogin)
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

