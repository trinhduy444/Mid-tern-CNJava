*Trịnh Trường Duy - 52000655*
*Mid-tern project Java Spring Commerce*
## Cách chạy ứng dụng
**Yêu cầu:** Đã cài các JDK 8, Intellij IDEA, MySQL, Git, Gradle.
- B1: Clone https://gitlab.duthu.net/S52000655/mid-tern-java.git về máy.
- B2: Mở project bằng Intellij IDEA.
- B3: Đầu tiên mình cần tạo database trên mysql với tên là spring commerce.
- B4: Sau đó mình import file: SpringCommerce.sql vào database vừa tạo.
- B5: Mình cần cài đặt các thư viện cần thiết cho ứng dụng trong gradle.
- B6: Mình cần cấu hình file application.properties trong thư mục resources xóa các db và mở comment các spring.datasource như video đã miêu tả.
- B7: Chạy ứng dụng và truy cập vào địa chỉ: http://localhost:3030/

**Account** Tôi đã cấu hình sẵn tài khoản admin và user để test ứng dụng:
- UserAccount: trinhduy444@gmail.com - 123456
- AdminAccount: admin@gmail.com  - 123456

## Giải thích
**1. Các nguyên tắc, mẫu phát triển phần mềm
và thực tiễn đang được áp dụng**
- Ứng dụng này được xây dựng bằng cách sử dụng mô hình phát triển phần mềm Agile Scrum và sử dụng các công nghệ như MyBatis, Thymeleaf, và Spring Security.

**2. Cấu trúc mã**
*Bên trong src/main/java/com/example/SpringCommerceShop*
- **Controller**: Chứa các controller của ứng dụng.
- **Config**: Chứa các Config của hệ thống.
- **Mapper**: Chứa các Mapper interface các truy vấn của ứng dụng.
- **Modal**: Chứa các Model của ứng dụng.

*Bên trong /src/main/resources*
- **static**: Chứa các file css, js, image, font.
- **templates**: Chứa các file html.

*generatorMyBatis.xml*
- Chứa các cấu hình của **MyBatis.**

*Bên trong src/main/resources/com/example/SpringCommerceShop/Mapper/Sql*

- Chứa các file sql của ứng dụng.
- Và các câu lênh truy vấn

**3. Các lệnh CURL xác minh API**
- curl -X GET 'http://localhost:3030/login' \
-H 'Content-Type: application/json' \
-d '{"email":"trinhduy444@gmail.com", "password":"123456"}'

- curl -X POST 'http://localhost:3030/purchaseSuccess' \
-H 'Content-Type: application/json' \
-d '{"email":"trinhduy444@gmail.com", "password":"123456"}'


**4. Spring security**
- Ứng dụng này đã được cung cấp bảo mật bằng Spring Security. Các chức năng bảo mật bao gồm xác thực người dùng và kiểm soát quyền truy cập. Bạn có thể cấu hình các tùy chọn bảo mật trong tệp application.properties.

Cảm ơn các bạn thầy đọc bài của em, Chúc thầy có một ngày tốt lành.