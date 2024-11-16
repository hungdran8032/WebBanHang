-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: localhost:3306
-- Thời gian đã tạo: Th10 15, 2024 lúc 12:27 PM
-- Phiên bản máy phục vụ: 8.0.30
-- Phiên bản PHP: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `web_ecommerce`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `address`
--

CREATE TABLE `address` (
  `id` bigint NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `locality` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `pin_code` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Đang đổ dữ liệu cho bảng `address`
--

INSERT INTO `address` (`id`, `address`, `city`, `locality`, `mobile`, `name`, `pin_code`, `state`) VALUES
(1, NULL, 'Thành phố XYZ', NULL, NULL, 'hung', '54231', 'Tỉnh ABC'),
(2, NULL, 'Thành phố XYZ', NULL, NULL, 'hung', '54231', 'Tỉnh ABC'),
(3, NULL, 'Thành phố XYZ', NULL, NULL, 'hung', '54231', 'Tỉnh ABC'),
(52, NULL, 'Thành phố XYZ', NULL, NULL, 'hung', '54231', 'Tỉnh ABC');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `address_seq`
--

CREATE TABLE `address_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Đang đổ dữ liệu cho bảng `address_seq`
--

INSERT INTO `address_seq` (`next_val`) VALUES
(151);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `cart`
--

CREATE TABLE `cart` (
  `id` bigint NOT NULL,
  `coupon_code` varchar(255) DEFAULT NULL,
  `discount` int NOT NULL,
  `total_item` int NOT NULL,
  `total_mrp_price` int NOT NULL,
  `total_selling_price` double NOT NULL,
  `user_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Đang đổ dữ liệu cho bảng `cart`
--

INSERT INTO `cart` (`id`, `coupon_code`, `discount`, `total_item`, `total_mrp_price`, `total_selling_price`, `user_id`) VALUES
(1, NULL, 0, 0, 0, 0, NULL),
(2, NULL, 0, 0, 0, 0, NULL),
(52, NULL, 0, 0, 0, 0, 102);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `cart_item`
--

CREATE TABLE `cart_item` (
  `id` bigint NOT NULL,
  `mrp_price` int DEFAULT NULL,
  `quantity` int NOT NULL,
  `selling_price` int DEFAULT NULL,
  `size` varchar(255) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `cart_id` bigint DEFAULT NULL,
  `product_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Đang đổ dữ liệu cho bảng `cart_item`
--

INSERT INTO `cart_item` (`id`, `mrp_price`, `quantity`, `selling_price`, `size`, `user_id`, `cart_id`, `product_id`) VALUES
(52, 8000, 8, 6400, 'M', 102, 52, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `cart_item_seq`
--

CREATE TABLE `cart_item_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Đang đổ dữ liệu cho bảng `cart_item_seq`
--

INSERT INTO `cart_item_seq` (`next_val`) VALUES
(151);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `cart_seq`
--

CREATE TABLE `cart_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Đang đổ dữ liệu cho bảng `cart_seq`
--

INSERT INTO `cart_seq` (`next_val`) VALUES
(151);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `category`
--

CREATE TABLE `category` (
  `id` bigint NOT NULL,
  `category_id` varchar(255) NOT NULL,
  `level` int NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `parent_category_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Đang đổ dữ liệu cho bảng `category`
--

INSERT INTO `category` (`id`, `category_id`, `level`, `name`, `parent_category_id`) VALUES
(4, 'Ao nam', 1, NULL, NULL),
(5, 'ao phong', 2, NULL, 4),
(6, 'ao thun', 3, NULL, 5);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `category_seq`
--

CREATE TABLE `category_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Đang đổ dữ liệu cho bảng `category_seq`
--

INSERT INTO `category_seq` (`next_val`) VALUES
(101);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `coupon`
--

CREATE TABLE `coupon` (
  `id` bigint NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `discount_percentage` double NOT NULL,
  `is_active` bit(1) NOT NULL,
  `minimum_order_value` double NOT NULL,
  `validity_end_date` date DEFAULT NULL,
  `validity_start_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `coupon_seq`
--

CREATE TABLE `coupon_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Đang đổ dữ liệu cho bảng `coupon_seq`
--

INSERT INTO `coupon_seq` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `deal`
--

CREATE TABLE `deal` (
  `id` bigint NOT NULL,
  `discount` int DEFAULT NULL,
  `category_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `deal_seq`
--

CREATE TABLE `deal_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Đang đổ dữ liệu cho bảng `deal_seq`
--

INSERT INTO `deal_seq` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `home_category`
--

CREATE TABLE `home_category` (
  `id` bigint NOT NULL,
  `category_id` varchar(255) DEFAULT NULL,
  `home_category_section` tinyint DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
) ;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `home_category_seq`
--

CREATE TABLE `home_category_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Đang đổ dữ liệu cho bảng `home_category_seq`
--

INSERT INTO `home_category_seq` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `orders`
--

CREATE TABLE `orders` (
  `id` bigint NOT NULL,
  `deliver_date` datetime(6) DEFAULT NULL,
  `discount` int DEFAULT NULL,
  `order_date` datetime(6) DEFAULT NULL,
  `order_id` varchar(255) DEFAULT NULL,
  `order_status` tinyint DEFAULT NULL,
  `payment_id` varchar(255) DEFAULT NULL,
  `razorpay_payment_id` varchar(255) DEFAULT NULL,
  `razorpay_payment_link_id` varchar(255) DEFAULT NULL,
  `razorpay_payment_link_reference_id` varchar(255) DEFAULT NULL,
  `razorpay_payment_link_status` varchar(255) DEFAULT NULL,
  `status` tinyint DEFAULT NULL,
  `payment_status` tinyint DEFAULT NULL,
  `seller_id` bigint DEFAULT NULL,
  `total_mrp_price` double NOT NULL,
  `total_price` int NOT NULL,
  `total_selling_price` int DEFAULT NULL,
  `shipping_address_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL
) ;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `orders_seq`
--

CREATE TABLE `orders_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Đang đổ dữ liệu cho bảng `orders_seq`
--

INSERT INTO `orders_seq` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `order_item`
--

CREATE TABLE `order_item` (
  `id` bigint NOT NULL,
  `mrp_price` int DEFAULT NULL,
  `quantity` int NOT NULL,
  `selling_price` int DEFAULT NULL,
  `size` varchar(255) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `order_id` bigint DEFAULT NULL,
  `product_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `order_item_seq`
--

CREATE TABLE `order_item_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Đang đổ dữ liệu cho bảng `order_item_seq`
--

INSERT INTO `order_item_seq` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `payment_order`
--

CREATE TABLE `payment_order` (
  `id` bigint NOT NULL,
  `amount` bigint DEFAULT NULL,
  `method` tinyint DEFAULT NULL,
  `payment_link_id` varchar(255) DEFAULT NULL,
  `status` tinyint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL
) ;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `payment_order_orders`
--

CREATE TABLE `payment_order_orders` (
  `payment_order_id` bigint NOT NULL,
  `orders_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `payment_order_seq`
--

CREATE TABLE `payment_order_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Đang đổ dữ liệu cho bảng `payment_order_seq`
--

INSERT INTO `payment_order_seq` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `product`
--

CREATE TABLE `product` (
  `id` bigint NOT NULL,
  `color` varchar(255) DEFAULT NULL,
  `create_at` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `discount_percent` int NOT NULL,
  `mrp_price` int NOT NULL,
  `num_ratings` int NOT NULL,
  `quantity` int NOT NULL,
  `selling_price` int NOT NULL,
  `size` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `category_id` bigint DEFAULT NULL,
  `seller_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Đang đổ dữ liệu cho bảng `product`
--

INSERT INTO `product` (`id`, `color`, `create_at`, `description`, `discount_percent`, `mrp_price`, `num_ratings`, `quantity`, `selling_price`, `size`, `title`, `category_id`, `seller_id`) VALUES
(1, 'Red', '2024-11-06 23:28:23.590978', 'This is an example product description.', 20, 1000, 0, 0, 800, 'L,M,S', 'Product Example', 6, 3),
(2, 'Red', '2024-11-06 23:45:12.539351', 'This is an example product description.', 20, 1000, 0, 0, 800, 'L,M,S', 'Product Example', 6, 3),
(52, 'Red', '2024-11-07 00:03:39.342362', 'This is an example product description.22222', 20, 1000, 0, 0, 800, 'L,M,S', 'Product Example 2222222', 6, 3);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `product_images`
--

CREATE TABLE `product_images` (
  `product_id` bigint NOT NULL,
  `images` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Đang đổ dữ liệu cho bảng `product_images`
--

INSERT INTO `product_images` (`product_id`, `images`) VALUES
(1, 'https://down-vn.img.susercontent.com/file/6aba1d32171c02c7e0c3d59a5f75fbb8'),
(1, 'https://fecilia.com/wp-content/uploads/2023/06/ao-thun-nam-co-tron-loang-trang-den-3-mau-tay-ngan-form-rong-ao-thun-mua-he-thoi-trang-nam-cotton-vai-day-min-fsm334-1.jpg'),
(2, 'https://down-vn.img.susercontent.com/file/6aba1d32171c02c7e0c3d59a5f75fbb8'),
(2, 'https://fecilia.com/wp-content/uploads/2023/06/ao-thun-nam-co-tron-loang-trang-den-3-mau-tay-ngan-form-rong-ao-thun-mua-he-thoi-trang-nam-cotton-vai-day-min-fsm334-1.jpg'),
(52, 'https://down-vn.img.susercontent.com/file/6aba1d32171c02c7e0c3d59a5f75fbb8'),
(52, 'https://fecilia.com/wp-content/uploads/2023/06/ao-thun-nam-co-tron-loang-trang-den-3-mau-tay-ngan-form-rong-ao-thun-mua-he-thoi-trang-nam-cotton-vai-day-min-fsm334-1.jpg');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `product_seq`
--

CREATE TABLE `product_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Đang đổ dữ liệu cho bảng `product_seq`
--

INSERT INTO `product_seq` (`next_val`) VALUES
(151);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `review`
--

CREATE TABLE `review` (
  `id` bigint NOT NULL,
  `create_at` datetime(6) NOT NULL,
  `rating` double NOT NULL,
  `review_text` varchar(255) NOT NULL,
  `product_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `review_product_images`
--

CREATE TABLE `review_product_images` (
  `review_id` bigint NOT NULL,
  `product_images` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `review_seq`
--

CREATE TABLE `review_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Đang đổ dữ liệu cho bảng `review_seq`
--

INSERT INTO `review_seq` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `seller`
--

CREATE TABLE `seller` (
  `id` bigint NOT NULL,
  `gstin` varchar(255) DEFAULT NULL,
  `account_status` tinyint DEFAULT NULL,
  `account_holder_name` varchar(255) DEFAULT NULL,
  `account_number` varchar(255) DEFAULT NULL,
  `ifsc_code` varchar(255) DEFAULT NULL,
  `banner` varchar(255) DEFAULT NULL,
  `business_address` varchar(255) DEFAULT NULL,
  `business_email` varchar(255) DEFAULT NULL,
  `business_mobile` varchar(255) DEFAULT NULL,
  `business_name` varchar(255) DEFAULT NULL,
  `logo` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `is_email_verified` bit(1) NOT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` tinyint DEFAULT NULL,
  `seller_name` varchar(255) DEFAULT NULL,
  `pickup_address_id` bigint DEFAULT NULL
) ;

--
-- Đang đổ dữ liệu cho bảng `seller`
--

INSERT INTO `seller` (`id`, `gstin`, `account_status`, `account_holder_name`, `account_number`, `ifsc_code`, `banner`, `business_address`, `business_email`, `business_mobile`, `business_name`, `logo`, `email`, `is_email_verified`, `mobile`, `password`, `role`, `seller_name`, `pickup_address_id`) VALUES
(1, NULL, 0, 'Nguyễn Văn A', '123456789', 'XYZ123456', 'khongco.png', 'Số 123, Đường ABC, Thành phố XYZ', 'contact@company.com', '0987654321', 'Công ty TNHH A', 'khongco.png', 'phamthanhtin919@gmail.com', b'0', '0123456789', '$2a$10$nNbPexUlwgazx2IJ7pX/I.e3dKdl/fyKIZ/UI3ICLkbxmaf6V8S1G', 2, 'Nguyễn Hùng', 1),
(2, NULL, 0, 'Nguyễn Văn A', '123456789', 'XYZ123456', 'khongco.png', 'Số 123, Đường ABC, Thành phố XYZ', 'contact@company.com', '0987654321', 'Công ty TNHH A', 'khongco.png', 'nguyenminhtaypro@gmail.com', b'1', '0123456789', '$2a$10$XaB.TVYjuIqve5DhxiMliuBCyha6DgR9A7nIxl.i45vj33P8ICxFm', 2, 'Nguyễn Hùng', 2),
(3, 'GSTIN123456', 0, 'Nguyễn Hung', '123456789', 'ABC123456', 'khongco.png', 'Số 123, Đường ABC, Thành phố XYZ', 'contact@company.com', '0987654321', 'Công ty TNHH A', 'khongco.png', 'oita752@gmail.com', b'1', '967061645', '$2a$10$NMYOSqNLeGKmr5y9/5Eo2evuEZdq.W8zx9U5zASNAz0Y3pHU8PXfi', 2, 'Nguyễn Hùng', 3);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `seller_report`
--

CREATE TABLE `seller_report` (
  `id` bigint NOT NULL,
  `canceled_orders` int DEFAULT NULL,
  `net_earnings` bigint DEFAULT NULL,
  `total_earnings` bigint DEFAULT NULL,
  `total_orders` int DEFAULT NULL,
  `total_refunds` bigint DEFAULT NULL,
  `total_sales` bigint DEFAULT NULL,
  `total_tax` bigint DEFAULT NULL,
  `total_transactions` int DEFAULT NULL,
  `seller_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `seller_report_seq`
--

CREATE TABLE `seller_report_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Đang đổ dữ liệu cho bảng `seller_report_seq`
--

INSERT INTO `seller_report_seq` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `seller_seq`
--

CREATE TABLE `seller_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Đang đổ dữ liệu cho bảng `seller_seq`
--

INSERT INTO `seller_seq` (`next_val`) VALUES
(151);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `transaction`
--

CREATE TABLE `transaction` (
  `id` bigint NOT NULL,
  `date` datetime(6) DEFAULT NULL,
  `customer_id` bigint DEFAULT NULL,
  `order_id` bigint DEFAULT NULL,
  `seller_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `transaction_seq`
--

CREATE TABLE `transaction_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Đang đổ dữ liệu cho bảng `transaction_seq`
--

INSERT INTO `transaction_seq` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user`
--

CREATE TABLE `user` (
  `id` bigint NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` tinyint DEFAULT NULL
) ;

--
-- Đang đổ dữ liệu cho bảng `user`
--

INSERT INTO `user` (`id`, `email`, `full_name`, `mobile`, `password`, `role`) VALUES
(1, 'minhhung@gmail.com', 'hung', NULL, NULL, 1),
(2, 'minhhung22222@gmail.com', 'hung2', '84967061645', '$2a$10$nbhbIUUnhdsV.Uv0KqVOHu2Y5SmxHNmkeCWGnrYln89w4/ZpHGntG', 1),
(52, 'nguyenhung20032308@gmail.com', 'hung23', '84967061645', '$2a$10$KQboxqI9jgNsySahKlb/Vu.byCjeaRzvOtX6yqBRANYFOifABcfmO', 1),
(102, 'oita752@gmail.com', 'hung23', '84967061645', '$2a$10$oHtC1QswqSuyivHxCO4uSu24enZhy62iSxu5nMphxK1.OADz4LQ0y', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user_addresses`
--

CREATE TABLE `user_addresses` (
  `user_id` bigint NOT NULL,
  `addresses_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user_seq`
--

CREATE TABLE `user_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Đang đổ dữ liệu cho bảng `user_seq`
--

INSERT INTO `user_seq` (`next_val`) VALUES
(201);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user_used_coupons`
--

CREATE TABLE `user_used_coupons` (
  `user_by_users_id` bigint NOT NULL,
  `used_coupons_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `verification_code`
--

CREATE TABLE `verification_code` (
  `id` bigint NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `otp` varchar(255) DEFAULT NULL,
  `seller_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Đang đổ dữ liệu cho bảng `verification_code`
--

INSERT INTO `verification_code` (`id`, `email`, `otp`, `seller_id`, `user_id`) VALUES
(2, 'minhhung22222@gmail.com', '400556', NULL, NULL),
(202, 'nguyenhung20032308@gmail.com', '561915', NULL, NULL),
(252, 'phamthanhtin919@gmail.com', '544679', NULL, NULL),
(253, 'nguyenminhtaypro@gmail.com', '556238', NULL, NULL),
(402, 'nguyenhung20032308@gmail.com', '170562', NULL, NULL),
(502, 'oita752@gmail.com', '933737', NULL, NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `verification_code_seq`
--

CREATE TABLE `verification_code_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Đang đổ dữ liệu cho bảng `verification_code_seq`
--

INSERT INTO `verification_code_seq` (`next_val`) VALUES
(601);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `wish_list`
--

CREATE TABLE `wish_list` (
  `id` bigint NOT NULL,
  `user_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `wish_list_products`
--

CREATE TABLE `wish_list_products` (
  `wish_list_id` bigint NOT NULL,
  `products_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `wish_list_seq`
--

CREATE TABLE `wish_list_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Đang đổ dữ liệu cho bảng `wish_list_seq`
--

INSERT INTO `wish_list_seq` (`next_val`) VALUES
(1);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `address`
--
ALTER TABLE `address`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `cart`
--
ALTER TABLE `cart`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK9emlp6m95v5er2bcqkjsw48he` (`user_id`);

--
-- Chỉ mục cho bảng `cart_item`
--
ALTER TABLE `cart_item`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK1uobyhgl1wvgt1jpccia8xxs3` (`cart_id`),
  ADD KEY `FKjcyd5wv4igqnw413rgxbfu4nv` (`product_id`);

--
-- Chỉ mục cho bảng `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKop35ifsyq39mxtmfs1asvbltv` (`category_id`),
  ADD KEY `FKs2ride9gvilxy2tcuv7witnxc` (`parent_category_id`);

--
-- Chỉ mục cho bảng `coupon`
--
ALTER TABLE `coupon`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `deal`
--
ALTER TABLE `deal`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK4mvlrtylin5pjn8y52o23b5io` (`category_id`);

--
-- Chỉ mục cho bảng `home_category`
--
ALTER TABLE `home_category`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKh0uue95ltjysfmkqb5abgk7tj` (`shipping_address_id`),
  ADD KEY `FKel9kyl84ego2otj2accfd8mr7` (`user_id`);

--
-- Chỉ mục cho bảng `order_item`
--
ALTER TABLE `order_item`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKt4dc2r9nbvbujrljv3e23iibt` (`order_id`),
  ADD KEY `FK551losx9j75ss5d6bfsqvijna` (`product_id`);

--
-- Chỉ mục cho bảng `payment_order`
--
ALTER TABLE `payment_order`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKdkj084gg4ak2uy5183lo7qu3q` (`user_id`);

--
-- Chỉ mục cho bảng `payment_order_orders`
--
ALTER TABLE `payment_order_orders`
  ADD PRIMARY KEY (`payment_order_id`,`orders_id`),
  ADD UNIQUE KEY `UK2ujbjdd8nj7rnnybygk520rov` (`orders_id`);

--
-- Chỉ mục cho bảng `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK1mtsbur82frn64de7balymq9s` (`category_id`),
  ADD KEY `FKesd6fy52tk7esoo2gcls4lfe3` (`seller_id`);

--
-- Chỉ mục cho bảng `product_images`
--
ALTER TABLE `product_images`
  ADD KEY `FKi8jnqq05sk5nkma3pfp3ylqrt` (`product_id`);

--
-- Chỉ mục cho bảng `review`
--
ALTER TABLE `review`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKiyof1sindb9qiqr9o8npj8klt` (`product_id`),
  ADD KEY `FKiyf57dy48lyiftdrf7y87rnxi` (`user_id`);

--
-- Chỉ mục cho bảng `review_product_images`
--
ALTER TABLE `review_product_images`
  ADD KEY `FKnh48ff6jnr2aym490rnn5q2ly` (`review_id`);

--
-- Chỉ mục cho bảng `seller`
--
ALTER TABLE `seller`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKcrgbovyy4gvgsum2yyb3fbfn7` (`email`),
  ADD UNIQUE KEY `UKjj970igwpuystjwkx6i5jp0o6` (`pickup_address_id`);

--
-- Chỉ mục cho bảng `seller_report`
--
ALTER TABLE `seller_report`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKled1p942vldbtov2q7fp264r1` (`seller_id`);

--
-- Chỉ mục cho bảng `transaction`
--
ALTER TABLE `transaction`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKpiu8sb2aby57a9iiuqe614hut` (`order_id`),
  ADD KEY `FKq9mudodpwcab2p6kt6tyfw9nj` (`customer_id`),
  ADD KEY `FKaaw4rl1eesbyd56wunm03tkpd` (`seller_id`);

--
-- Chỉ mục cho bảng `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `user_addresses`
--
ALTER TABLE `user_addresses`
  ADD PRIMARY KEY (`user_id`,`addresses_id`),
  ADD UNIQUE KEY `UKi5lp1fvgfvsplfqwu4ovwpnxs` (`addresses_id`);

--
-- Chỉ mục cho bảng `user_used_coupons`
--
ALTER TABLE `user_used_coupons`
  ADD PRIMARY KEY (`user_by_users_id`,`used_coupons_id`),
  ADD KEY `FK6v6jcmjfnqrg0gesn4udodddf` (`used_coupons_id`);

--
-- Chỉ mục cho bảng `verification_code`
--
ALTER TABLE `verification_code`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKdmpcy75hhu65imv7xw7ui6okw` (`seller_id`),
  ADD UNIQUE KEY `UKn576esytmxxfkgon3ja83h5vp` (`user_id`);

--
-- Chỉ mục cho bảng `wish_list`
--
ALTER TABLE `wish_list`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKr1w2h67p25icr918heol9jisu` (`user_id`);

--
-- Chỉ mục cho bảng `wish_list_products`
--
ALTER TABLE `wish_list_products`
  ADD PRIMARY KEY (`wish_list_id`,`products_id`),
  ADD KEY `FKru03wa8q3620c8f8brxki4478` (`products_id`);

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `cart`
--
ALTER TABLE `cart`
  ADD CONSTRAINT `FKl70asp4l4w0jmbm1tqyofho4o` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Các ràng buộc cho bảng `cart_item`
--
ALTER TABLE `cart_item`
  ADD CONSTRAINT `FK1uobyhgl1wvgt1jpccia8xxs3` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`id`),
  ADD CONSTRAINT `FKjcyd5wv4igqnw413rgxbfu4nv` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`);

--
-- Các ràng buộc cho bảng `category`
--
ALTER TABLE `category`
  ADD CONSTRAINT `FKs2ride9gvilxy2tcuv7witnxc` FOREIGN KEY (`parent_category_id`) REFERENCES `category` (`id`);

--
-- Các ràng buộc cho bảng `deal`
--
ALTER TABLE `deal`
  ADD CONSTRAINT `FK14u5urp9o5t1vwhvk47npo005` FOREIGN KEY (`category_id`) REFERENCES `home_category` (`id`);

--
-- Các ràng buộc cho bảng `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `FKel9kyl84ego2otj2accfd8mr7` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKh0uue95ltjysfmkqb5abgk7tj` FOREIGN KEY (`shipping_address_id`) REFERENCES `address` (`id`);

--
-- Các ràng buộc cho bảng `order_item`
--
ALTER TABLE `order_item`
  ADD CONSTRAINT `FK551losx9j75ss5d6bfsqvijna` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  ADD CONSTRAINT `FKt4dc2r9nbvbujrljv3e23iibt` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`);

--
-- Các ràng buộc cho bảng `payment_order`
--
ALTER TABLE `payment_order`
  ADD CONSTRAINT `FKdkj084gg4ak2uy5183lo7qu3q` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Các ràng buộc cho bảng `payment_order_orders`
--
ALTER TABLE `payment_order_orders`
  ADD CONSTRAINT `FKc9rqjylj0f0w18f5n32stg5o7` FOREIGN KEY (`orders_id`) REFERENCES `orders` (`id`),
  ADD CONSTRAINT `FKg5ba6n6ksqou77epbn9h6738t` FOREIGN KEY (`payment_order_id`) REFERENCES `payment_order` (`id`);

--
-- Các ràng buộc cho bảng `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `FK1mtsbur82frn64de7balymq9s` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  ADD CONSTRAINT `FKesd6fy52tk7esoo2gcls4lfe3` FOREIGN KEY (`seller_id`) REFERENCES `seller` (`id`);

--
-- Các ràng buộc cho bảng `product_images`
--
ALTER TABLE `product_images`
  ADD CONSTRAINT `FKi8jnqq05sk5nkma3pfp3ylqrt` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`);

--
-- Các ràng buộc cho bảng `review`
--
ALTER TABLE `review`
  ADD CONSTRAINT `FKiyf57dy48lyiftdrf7y87rnxi` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKiyof1sindb9qiqr9o8npj8klt` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`);

--
-- Các ràng buộc cho bảng `review_product_images`
--
ALTER TABLE `review_product_images`
  ADD CONSTRAINT `FKnh48ff6jnr2aym490rnn5q2ly` FOREIGN KEY (`review_id`) REFERENCES `review` (`id`);

--
-- Các ràng buộc cho bảng `seller`
--
ALTER TABLE `seller`
  ADD CONSTRAINT `FK211igkobsc9a1ujun15vg8yd` FOREIGN KEY (`pickup_address_id`) REFERENCES `address` (`id`);

--
-- Các ràng buộc cho bảng `seller_report`
--
ALTER TABLE `seller_report`
  ADD CONSTRAINT `FKryorwmoc988nw53yhins0xwl8` FOREIGN KEY (`seller_id`) REFERENCES `seller` (`id`);

--
-- Các ràng buộc cho bảng `transaction`
--
ALTER TABLE `transaction`
  ADD CONSTRAINT `FKa8ufxrrpq6xmniblly0v1rhu8` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  ADD CONSTRAINT `FKaaw4rl1eesbyd56wunm03tkpd` FOREIGN KEY (`seller_id`) REFERENCES `seller` (`id`),
  ADD CONSTRAINT `FKq9mudodpwcab2p6kt6tyfw9nj` FOREIGN KEY (`customer_id`) REFERENCES `user` (`id`);

--
-- Các ràng buộc cho bảng `user_addresses`
--
ALTER TABLE `user_addresses`
  ADD CONSTRAINT `FKfm6x520mag23hvgr1oshaut8b` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKth1icmttmhhorb9wiarm73i06` FOREIGN KEY (`addresses_id`) REFERENCES `address` (`id`);

--
-- Các ràng buộc cho bảng `user_used_coupons`
--
ALTER TABLE `user_used_coupons`
  ADD CONSTRAINT `FK6v6jcmjfnqrg0gesn4udodddf` FOREIGN KEY (`used_coupons_id`) REFERENCES `coupon` (`id`),
  ADD CONSTRAINT `FKimolwdvn9k0ylke8d2wjfc3ys` FOREIGN KEY (`user_by_users_id`) REFERENCES `user` (`id`);

--
-- Các ràng buộc cho bảng `verification_code`
--
ALTER TABLE `verification_code`
  ADD CONSTRAINT `FK6xv2fg9bam0hdm7ybw71a8x40` FOREIGN KEY (`seller_id`) REFERENCES `seller` (`id`),
  ADD CONSTRAINT `FKgy5dhio3a6c9me7s0x9v1y4d2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Các ràng buộc cho bảng `wish_list`
--
ALTER TABLE `wish_list`
  ADD CONSTRAINT `FK8462y9kc76hpxuom1ui7dvp7k` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Các ràng buộc cho bảng `wish_list_products`
--
ALTER TABLE `wish_list_products`
  ADD CONSTRAINT `FKemcyikw15sfq3dcg689e29it1` FOREIGN KEY (`wish_list_id`) REFERENCES `wish_list` (`id`),
  ADD CONSTRAINT `FKru03wa8q3620c8f8brxki4478` FOREIGN KEY (`products_id`) REFERENCES `product` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
