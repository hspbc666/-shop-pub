// 厦门大学计算机专业 | 前华为工程师
// 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
// 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
// 公众号：蓝不蓝编程

import SwiftUI
import SwiftyJSON
import HandyJSON

struct AddNoteView: View {
    @State var text: String = ""
    var lblViewModel: LblViewModel
    @Environment(\.presentationMode) var presentationMode: Binding<PresentationMode>
    
    var body: some View {
        VStack {
            TextField("请输入内容...", text: $text)
            Spacer()
        }
        .padding()
        .navigationBarTitle(Text("编辑"),displayMode: .inline)
        .navigationBarItems(trailing: Button("保存") {
            saveNote()
        })
    }
    
    private func saveNote(){
        lblViewModel.addData(content: text){
            lblViewModel.queryData()
            goBack()
        }
    }
    private func goBack()
    {
        self.presentationMode.wrappedValue.dismiss()
    }
}

//struct AddNoteView_Previews: PreviewProvider {
//    static var previews: some View {
//        Group {
//            AddNoteView(refreshViewModel:RefreshViewModel())
//        }
//        .previewLayout(.fixed(width: 300, height: 50))
//    }
//}
