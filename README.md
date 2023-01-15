# Giới thiệu

Dự án sử dụng spring boot để tạo Rest API cho app client [MessengerLite](https://github.com/MyFirstGitEver/MessengerLite-full)(hỗ trợ trò chuyện trong thời gian thực).

# Cấu trúc project:
* configuration: package chứa các thiết lập end-point gửi nhận tin nhắn cho WebSocket server/client.
* controllers: package chứa các controller thiết lập các REST endpoint hỗ trợ truy vấn dữ liệu
* repositories: package chứa các repository truy vấn database.
* dtos: Các lớp đối tượng truyền/nhận thông tin giữa server và client
* entities: Các lớp đối tượng đại diện cho bảng bên trong database
