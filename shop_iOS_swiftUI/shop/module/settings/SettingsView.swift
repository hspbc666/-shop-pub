// 厦门大学计算机专业 | 前华为工程师
// 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
// 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
// 公众号：蓝不蓝编程

import SwiftUI

struct SettingsView: View {
    var body: some View {
        VStack{
            VStack{
                HStack{
                    Image(systemName: "person.circle.fill")
                        .resizable()
                        .frame(width: 50, height: 50)
                        .foregroundColor(Color.main_color)
                        .padding(EdgeInsets.init(top: 0, leading: 10, bottom: 0, trailing: 0))
                    VStack{
                        Text("尊贵会员").font(.title2)
                        Text("级别：白银").foregroundColor(Color(hex: 0x9E9EA2))
                    }
                    Spacer()
                    Text("查看个人资料").font(.body).foregroundColor(Color(hex: 0x595D63))
                    Image(systemName: "chevron.right").foregroundColor(Color(hex: 0x595D63))
                }
                Divider()
                HStack{
                    Text("收货地址")
                    Spacer()
                    Image(systemName: "chevron.right").foregroundColor(Color(hex: 0x595D63))
                }
            }
            .padding()
            .background(.white)
            
            
        }
        .frame(maxWidth: .infinity,maxHeight: .infinity, alignment: .top)
        .background(Color(hex: 0xF4F4F4))
    }
}

struct SettingsView_Previews: PreviewProvider {
    static var previews: some View {
        SettingsView()
    }
}
