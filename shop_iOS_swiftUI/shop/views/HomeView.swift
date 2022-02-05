// 厦门大学计算机专业 | 前华为工程师
// 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
// 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
// 公众号：蓝不蓝编程

import SwiftUI

struct HomeView: View {
    let images: [String] = [
        "https://h2.appsimg.com/a.appsimg.com/upload/brand/upcb/2022/01/06/102/ias_a28a4efde3be0890bb22724e5dedaeb5_1135x545_85.jpg",
        "https://h2.appsimg.com/a.appsimg.com/upload/brand/upcb/2021/07/30/160/ias_86095df3cfe17ce6437098d7d0519d9f_1135x545_85.jpg"
    ]
    @State var imageIndex: Int = 0
    @State var showImageDetail: Bool = false
    
    
    var body: some View {
        LblBanner(images: images, height: 200, index: $imageIndex)
            .onTapGesture {
                showImageDetail = true
            }
        
        if showImageDetail {
            //                ImageDetail(images: images, index: $imageIndex)
            //                    .onTapGesture {
            //                        show_img_detail = false
            //                    }
        }
    }
}

struct HomeView_Previews: PreviewProvider {
    static var previews: some View {
        HomeView()
    }
}
