// 厦门大学计算机专业 | 前华为工程师
// 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
// 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
// 公众号：蓝不蓝编程

import SwiftUI

struct MineView: View {
    var body: some View {
        VStack{
            HStack{
                Button(action:{ print("我被点啦")}){
                    Image("settings")
                        .resizable()
                        .frame(width: 40, height: 40)
                }.padding()
            }.frame(maxWidth: .infinity,alignment: .trailing)
            HStack{
                Image(systemName: "person.circle.fill")
                    .resizable()
                    .frame(width: 50, height: 50)
                    .foregroundColor(Color.main_color)
                    .padding(EdgeInsets.init(top: 0, leading: 10, bottom: 0, trailing: 0))
                VStack{
                    Text("尊贵会员").font(.title2)
                    Text("级别：白银")
                        .padding(EdgeInsets.init(top: 5, leading: 15, bottom: 5, trailing: 15))
                        .background(Color(hex: 0xD3D3F7))
                        .clipShape(RoundedRectangle(cornerRadius: 20))
                }
                Spacer()
            }
            Text("Mine!")
                .padding()
            Spacer()
            Text("wwwwwwwwww")
        }
        
    }
}

struct MineView_Previews: PreviewProvider {
    static var previews: some View {
        MineView()
    }
}
