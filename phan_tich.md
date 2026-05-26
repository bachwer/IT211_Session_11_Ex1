1. Lỗi Tính phí cân nặng với số lẻ

Quy Tắc nghiệp vụ

- 1kg đầu tiên 50.0000
- Mỗi kg tiếp theo hoặc phân số của kg + 10k

Điều này có nghĩa

- 1.1kg -> tính thêm 1kg
- 1.5kg -> tính thêm 1kg
- 2.3kg -> tính thêm 2kg

Tức là phần vượt quá 1kg phải được làm tròn lên


code hiện tại !
```Java
weightFee = 50000 + (Math.floor(weightKg - 1) * 10000);
```

Vấn đề: Math.floor() => là làm tròn xuống


VD: 
1.5 - 0.5  -> phí thêm 0
2.3 - 1.3 -> phí thêm 10.000


=> sai nghiệp vụ


Kết quả đúng phải là

1.5 vượt 0.5 => phí thêm 10k
2.3 vượt 1.3 => phí thêm 20k


Lỗi logic

Hệ thống dùng Math.floor() thay vì Math.ceil().

2. Lỗi tính phí khoảng cách ở ngưỡng biên

Quy tắc nghiệp vụ

* < 10km → không phí
* 10km đến <50km → 5.000/km
* >= 50km → 4.000/km
  
```java
if (distanceKm < 10) {
    distanceFee = 0;
} else if (distanceKm < 50) {
    distanceFee = distanceKm * 5000;
} else {
    distanceFee = distanceKm * 4000;
}
```

Phân tích

Trường hợp 49km: 49 * 5000 = 245000 (Logic hiện tại đúng.)

Trường hợp 50km: 50 * 4000 = 200000
Theo business rule hiện tại thì đúng.

Tuy nhiên vấn đề nằm ở:

* ngưỡng chuyển đổi đột ngột
* dễ xảy ra bug nếu điều kiện biên viết sai (<= thay vì <)

Ví dụ nếu viết: else if (distanceKm <= 50)

thì 50km sẽ bị tính sai thành: 50 * 5000 = 250000
