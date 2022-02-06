// 厦门大学计算机专业 | 前华为工程师
// 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
// 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
// 公众号：蓝不蓝编程

import SwiftUI

struct MineView: View {
    var body: some View {
        NavigationView{
            VStack{
                HStack{
                    NavigationLink(destination: SettingsView()) {
                        Image("settings")
                            .resizable()
                            .frame(width: 30, height: 30)
                            .padding()
                    }.navigationBarTitle(Text(""), displayMode: .inline)
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
                VStack{
                    HStack{
                        Text("我的订单").font(.title3)
                        Spacer()
                        NavigationLink(destination: OrderListView()) {
                            Text("全部订单").font(.body).foregroundColor(Color(hex: 0x595D63))
                            Image(systemName: "chevron.right").foregroundColor(Color(hex: 0x595D63))
                        }
                    }
                    HStack{
                        NavigationLink(destination: OrderListView()) {
                            VStack{
                                Image("to_pay").resizable().frame(width: 40, height: 40)
                                Text("待付款").font(.body).foregroundColor(Color(hex: 0x595D63))
                            }
                        }
                        Spacer()
                        NavigationLink(destination: OrderListView()) {
                            VStack{
                                Image("to_deliver").resizable().frame(width: 40, height: 40)
                                Text("待发货").font(.body).foregroundColor(Color(hex: 0x595D63))
                            }
                        }
                        Spacer()
                        NavigationLink(destination: OrderListView()) {
                            VStack{
                                Image("to_receive").resizable().frame(width: 40, height: 40)
                                Text("待收货").font(.body).foregroundColor(Color(hex: 0x595D63))
                            }
                        }
                        Spacer()
                        NavigationLink(destination: OrderListView()) {
                            VStack{
                                Image("to_comment").resizable().frame(width: 40, height: 40)
                                Text("待评价").font(.body).foregroundColor(Color(hex: 0x595D63))
                            }
                        }
                        Spacer()
                        NavigationLink(destination: OrderListView()) {
                            VStack{
                                Image("to_return").resizable().frame(width: 40, height: 40)
                                Text("退换/售后").font(.body).foregroundColor(Color(hex: 0x595D63))
                            }
                        }
                    }
                }.padding(EdgeInsets.init(top: 20, leading: 10, bottom: 10, trailing: 10))
                    .frame(maxWidth: .infinity,alignment: .leading)
                    .background(.white)
                    .clipShape(RoundedRectangle(cornerRadius: 5))
                    .padding()
            }
            .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .topLeading)
            .background(Color(hex: 0xF4F4F4))
        }
    }
}

struct MineView_Previews: PreviewProvider {
    static var previews: some View {
        MineView()
    }
}
