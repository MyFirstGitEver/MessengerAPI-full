# Giới thiệu

Dự án sử dụng framework Spring Boot để tạo Rest API cho app client [MessengerLite](https://github.com/MyFirstGitEver/MessengerLite-full)(hỗ trợ trò chuyện trong thời gian thực).

[REST Api](https://en.wikipedia.org/wiki/Representational_state_transfer)  
[Spring framework](https://vi.wikipedia.org/wiki/Spring_Framework)  

# Cấu trúc project:
* configuration: package chứa các thiết lập end-point gửi nhận tin nhắn cho WebSocket server/client.
* controllers: package chứa các controller thiết lập các REST endpoint hỗ trợ truy vấn dữ liệu
* repositories: package chứa các repository truy vấn database.
* dtos: Các lớp đối tượng truyền/nhận thông tin giữa server và client
* entities: Các lớp đối tượng đại diện cho bảng bên trong database

# Diagram

![diagram](https://user-images.githubusercontent.com/113078180/212550654-6921ca8b-cb52-4e93-ba45-0cba1bc5d50c.png)
