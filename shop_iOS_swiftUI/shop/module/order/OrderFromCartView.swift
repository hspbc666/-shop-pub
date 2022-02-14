// 厦门大学计算机专业 | 前华为工程师
// 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
// 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
// 公众号：蓝不蓝编程

import SwiftUI

struct OrderFromCartView: View {
    var goodsSum = 30
    var body: some View {
        Text("Hello, world!")
            .padding()
        Spacer()
        Divider()
        HStack{
            Spacer()
            Text("实付款")
            Text("￥\(goodsSum)").foregroundColor(Color.main_color)
            Spacer()
            NavigationLink(destination: OrderFromCartView()){
                Text("提交订单").font(.headline).frame(minWidth: 150)
                    .padding(EdgeInsets.init(top: 5, leading: 0, bottom: 5, trailing: 0))
                    .foregroundColor(.white)
                    .background(Color.main_color)
                    .clipShape(RoundedRectangle(cornerRadius: 5))
            }
            
            Spacer()
        } .padding(EdgeInsets.init(top: 0, leading: 0, bottom: 5, trailing: 0))
    }
}

struct OrderFromCartView_Previews: PreviewProvider {
    static var previews: some View {
        OrderFromCartView()
    }
}
