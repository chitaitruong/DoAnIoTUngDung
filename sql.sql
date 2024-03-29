USE [master]
GO
/****** Object:  Database [test3]    Script Date: 21-Apr-23 01:37:41 PM ******/
CREATE DATABASE [test3]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'test3', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.CHITAITRUONG01\MSSQL\DATA\test3.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'test3_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.CHITAITRUONG01\MSSQL\DATA\test3_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [test3] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [test3].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [test3] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [test3] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [test3] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [test3] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [test3] SET ARITHABORT OFF 
GO
ALTER DATABASE [test3] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [test3] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [test3] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [test3] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [test3] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [test3] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [test3] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [test3] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [test3] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [test3] SET  DISABLE_BROKER 
GO
ALTER DATABASE [test3] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [test3] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [test3] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [test3] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [test3] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [test3] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [test3] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [test3] SET RECOVERY FULL 
GO
ALTER DATABASE [test3] SET  MULTI_USER 
GO
ALTER DATABASE [test3] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [test3] SET DB_CHAINING OFF 
GO
ALTER DATABASE [test3] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [test3] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [test3] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [test3] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'test3', N'ON'
GO
ALTER DATABASE [test3] SET QUERY_STORE = OFF
GO
USE [test3]
GO
USE [test3]
GO
/****** Object:  Sequence [dbo].[hibernate_sequence]    Script Date: 21-Apr-23 01:37:46 PM ******/
CREATE SEQUENCE [dbo].[hibernate_sequence] 
 AS [bigint]
 START WITH 1
 INCREMENT BY 1
 MINVALUE -9223372036854775808
 MAXVALUE 9223372036854775807
 CACHE 
GO
/****** Object:  Table [dbo].[diemdanh]    Script Date: 21-Apr-23 01:37:46 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[diemdanh](
	[sinhvien_id] [bigint] NOT NULL,
	[ghi_chu] [varchar](255) NULL,
	[thoi_gian_cap_nhat] [datetime2](7) NULL,
	[thoi_gian_quet_van_tay] [datetime2](7) NULL,
	[trang_thai] [bigint] NOT NULL,
	[loptinchi_id] [bigint] NOT NULL,
	[ngay_id] [bigint] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[loptinchi_id] ASC,
	[ngay_id] ASC,
	[sinhvien_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[giangvien]    Script Date: 21-Apr-23 01:37:46 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[giangvien](
	[id] [bigint] NOT NULL,
	[khoa_id] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[hedaotao]    Script Date: 21-Apr-23 01:37:46 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[hedaotao](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[ten_hedaotao] [varchar](255) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[hocky]    Script Date: 21-Apr-23 01:37:46 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[hocky](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[hocky] [bigint] NOT NULL,
	[namhoc] [varchar](255) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[khoa]    Script Date: 21-Apr-23 01:37:46 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[khoa](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[ten_khoa] [varchar](255) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[lop]    Script Date: 21-Apr-23 01:37:46 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[lop](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[khoahoc] [varchar](255) NOT NULL,
	[ma_lop] [varchar](255) NOT NULL,
	[giangvien_id] [bigint] NOT NULL,
	[hedaotao_id] [bigint] NOT NULL,
	[nganh_id] [bigint] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[loptinchi]    Script Date: 21-Apr-23 01:37:46 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[loptinchi](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[sl_sinhvien] [bigint] NOT NULL,
	[so_tiet] [bigint] NOT NULL,
	[thu] [varchar](255) NOT NULL,
	[tiet_bd] [bigint] NOT NULL,
	[giangvien_id] [bigint] NOT NULL,
	[hocky_id] [bigint] NOT NULL,
	[monhoc_id] [bigint] NOT NULL,
	[phong_id] [bigint] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[loptinchi_lop]    Script Date: 21-Apr-23 01:37:46 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[loptinchi_lop](
	[loptinchi_id] [bigint] NOT NULL,
	[lop_id] [bigint] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[loptinchi_id] ASC,
	[lop_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[loptinchi_sinhvien]    Script Date: 21-Apr-23 01:37:46 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[loptinchi_sinhvien](
	[loptinchi_id] [bigint] NOT NULL,
	[sinhvien_id] [bigint] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[loptinchi_id] ASC,
	[sinhvien_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[loptinchingay]    Script Date: 21-Apr-23 01:37:46 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[loptinchingay](
	[loptinchi_id] [bigint] NOT NULL,
	[ngay_id] [bigint] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[loptinchi_id] ASC,
	[ngay_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[monhoc]    Script Date: 21-Apr-23 01:37:46 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[monhoc](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[ma] [varchar](255) NOT NULL,
	[monhoc] [varchar](255) NOT NULL,
	[so_buoilt] [int] NOT NULL,
	[so_buoith] [int] NOT NULL,
	[stc] [int] NOT NULL,
	[stc_hp] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[nganh]    Script Date: 21-Apr-23 01:37:46 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[nganh](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[ma_nganh] [varchar](255) NOT NULL,
	[so_nam] [float] NOT NULL,
	[ten_nganh] [varchar](255) NOT NULL,
	[khoa_id] [bigint] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ngay]    Script Date: 21-Apr-23 01:37:46 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ngay](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[ngay] [date] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[phong]    Script Date: 21-Apr-23 01:37:46 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[phong](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[ten_phong] [varchar](255) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[refreshtoken]    Script Date: 21-Apr-23 01:37:46 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[refreshtoken](
	[id] [bigint] NOT NULL,
	[expiry_date] [datetime2](7) NOT NULL,
	[token] [varchar](255) NOT NULL,
	[user_id] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[roles]    Script Date: 21-Apr-23 01:37:46 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[roles](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[name] [varchar](20) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[sinhvien]    Script Date: 21-Apr-23 01:37:46 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[sinhvien](
	[id] [bigint] NOT NULL,
	[lop_id] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[user_roles]    Script Date: 21-Apr-23 01:37:46 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[user_roles](
	[user_id] [bigint] NOT NULL,
	[role_id] [bigint] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[user_id] ASC,
	[role_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[users]    Script Date: 21-Apr-23 01:37:46 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[users](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[address] [varchar](255) NOT NULL,
	[email] [varchar](50) NOT NULL,
	[hoten] [varchar](255) NOT NULL,
	[ngaysinh] [date] NOT NULL,
	[password] [varchar](120) NOT NULL,
	[phone] [varchar](255) NOT NULL,
	[username] [varchar](20) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[diemdanh] ([sinhvien_id], [ghi_chu], [thoi_gian_cap_nhat], [thoi_gian_quet_van_tay], [trang_thai], [loptinchi_id], [ngay_id]) VALUES (4, N'', CAST(N'2023-04-20T13:32:08.7480722' AS DateTime2), CAST(N'2023-04-19T21:02:49.2696949' AS DateTime2), 0, 1, 2)
INSERT [dbo].[diemdanh] ([sinhvien_id], [ghi_chu], [thoi_gian_cap_nhat], [thoi_gian_quet_van_tay], [trang_thai], [loptinchi_id], [ngay_id]) VALUES (5, NULL, CAST(N'2023-04-19T21:12:28.8537319' AS DateTime2), CAST(N'2023-04-19T21:12:28.8537319' AS DateTime2), 1, 1, 2)
GO
INSERT [dbo].[giangvien] ([id], [khoa_id]) VALUES (1, NULL)
INSERT [dbo].[giangvien] ([id], [khoa_id]) VALUES (2, NULL)
INSERT [dbo].[giangvien] ([id], [khoa_id]) VALUES (3, NULL)
GO
SET IDENTITY_INSERT [dbo].[hedaotao] ON 

INSERT [dbo].[hedaotao] ([id], [ten_hedaotao]) VALUES (1, N'DHCQ')
SET IDENTITY_INSERT [dbo].[hedaotao] OFF
GO
SET IDENTITY_INSERT [dbo].[hocky] ON 

INSERT [dbo].[hocky] ([id], [hocky], [namhoc]) VALUES (1, 1, N'2021-2022')
INSERT [dbo].[hocky] ([id], [hocky], [namhoc]) VALUES (2, 2, N'2021-2022')
INSERT [dbo].[hocky] ([id], [hocky], [namhoc]) VALUES (3, 3, N'2021-2022')
INSERT [dbo].[hocky] ([id], [hocky], [namhoc]) VALUES (4, 1, N'2022-2023')
INSERT [dbo].[hocky] ([id], [hocky], [namhoc]) VALUES (5, 2, N'2022-2023')
INSERT [dbo].[hocky] ([id], [hocky], [namhoc]) VALUES (6, 3, N'2022-2023')
SET IDENTITY_INSERT [dbo].[hocky] OFF
GO
SET IDENTITY_INSERT [dbo].[khoa] ON 

INSERT [dbo].[khoa] ([id], [ten_khoa]) VALUES (1, N'Cong nghe thong tin 2')
SET IDENTITY_INSERT [dbo].[khoa] OFF
GO
SET IDENTITY_INSERT [dbo].[loptinchi] ON 

INSERT [dbo].[loptinchi] ([id], [sl_sinhvien], [so_tiet], [thu], [tiet_bd], [giangvien_id], [hocky_id], [monhoc_id], [phong_id]) VALUES (1, 100, 4, N'Nam', 1, 2, 5, 1, 2)
SET IDENTITY_INSERT [dbo].[loptinchi] OFF
GO
INSERT [dbo].[loptinchi_sinhvien] ([loptinchi_id], [sinhvien_id]) VALUES (1, 4)
INSERT [dbo].[loptinchi_sinhvien] ([loptinchi_id], [sinhvien_id]) VALUES (1, 5)
GO
INSERT [dbo].[loptinchingay] ([loptinchi_id], [ngay_id]) VALUES (1, 1)
INSERT [dbo].[loptinchingay] ([loptinchi_id], [ngay_id]) VALUES (1, 2)
GO
SET IDENTITY_INSERT [dbo].[monhoc] ON 

INSERT [dbo].[monhoc] ([id], [ma], [monhoc], [so_buoilt], [so_buoith], [stc], [stc_hp]) VALUES (1, N'INT14102', N'Cac ky thuat giau tin', 7, 2, 2, 2)
INSERT [dbo].[monhoc] ([id], [ma], [monhoc], [so_buoilt], [so_buoith], [stc], [stc_hp]) VALUES (2, N'INT14106', N'Quan ly an toan thong tin', 10, 2, 3, 3)
INSERT [dbo].[monhoc] ([id], [ma], [monhoc], [so_buoilt], [so_buoith], [stc], [stc_hp]) VALUES (3, N'INT14107', N'Kiem thu xam nhap', 10, 3, 3, 3)
INSERT [dbo].[monhoc] ([id], [ma], [monhoc], [so_buoilt], [so_buoith], [stc], [stc_hp]) VALUES (4, N'INT1429-2', N'Ky thuat theo doi, giam sat an toan mang', 7, 2, 2, 2)
INSERT [dbo].[monhoc] ([id], [ma], [monhoc], [so_buoilt], [so_buoith], [stc], [stc_hp]) VALUES (5, N'INT1449', N'Phat trien ung dung cho cac thiet bi di dong', 10, 3, 3, 3)
INSERT [dbo].[monhoc] ([id], [ma], [monhoc], [so_buoilt], [so_buoith], [stc], [stc_hp]) VALUES (6, N'INT1483', N'An toan mang nang cao', 9, 4, 3, 3)
SET IDENTITY_INSERT [dbo].[monhoc] OFF
GO
SET IDENTITY_INSERT [dbo].[nganh] ON 

INSERT [dbo].[nganh] ([id], [ma_nganh], [so_nam], [ten_nganh], [khoa_id]) VALUES (1, N'7480201', 4.5, N'Cong nghe thong tin', 1)
INSERT [dbo].[nganh] ([id], [ma_nganh], [so_nam], [ten_nganh], [khoa_id]) VALUES (2, N'7480202', 4.5, N'An toan thong tin', 1)
SET IDENTITY_INSERT [dbo].[nganh] OFF
GO
SET IDENTITY_INSERT [dbo].[ngay] ON 

INSERT [dbo].[ngay] ([id], [ngay]) VALUES (1, CAST(N'2023-03-19' AS Date))
INSERT [dbo].[ngay] ([id], [ngay]) VALUES (2, CAST(N'2023-03-12' AS Date))
SET IDENTITY_INSERT [dbo].[ngay] OFF
GO
SET IDENTITY_INSERT [dbo].[phong] ON 

INSERT [dbo].[phong] ([id], [ten_phong]) VALUES (1, N'2B11')
INSERT [dbo].[phong] ([id], [ten_phong]) VALUES (2, N'2B24')
INSERT [dbo].[phong] ([id], [ten_phong]) VALUES (3, N'2B32')
INSERT [dbo].[phong] ([id], [ten_phong]) VALUES (4, N'2B34')
SET IDENTITY_INSERT [dbo].[phong] OFF
GO
INSERT [dbo].[refreshtoken] ([id], [expiry_date], [token], [user_id]) VALUES (10, CAST(N'2023-04-19T22:49:05.7614446' AS DateTime2), N'525293f7-0381-4719-87e2-2ef2f71884ad', 1)
INSERT [dbo].[refreshtoken] ([id], [expiry_date], [token], [user_id]) VALUES (11, CAST(N'2023-04-19T22:55:00.2972187' AS DateTime2), N'4ec59c12-915f-43b1-8ae5-9701f98c08c4', 1)
INSERT [dbo].[refreshtoken] ([id], [expiry_date], [token], [user_id]) VALUES (12, CAST(N'2023-04-19T23:06:56.7064939' AS DateTime2), N'2f0ecad2-3b82-4f7f-bf88-0dfc86d7ffa0', 2)
INSERT [dbo].[refreshtoken] ([id], [expiry_date], [token], [user_id]) VALUES (13, CAST(N'2023-04-19T23:17:16.1603113' AS DateTime2), N'6e43248c-8214-440f-ac4e-f803d0dd0487', 2)
INSERT [dbo].[refreshtoken] ([id], [expiry_date], [token], [user_id]) VALUES (14, CAST(N'2023-04-19T23:31:46.0491533' AS DateTime2), N'43a226d6-21b9-4560-b258-64c965f0e4c5', 2)
INSERT [dbo].[refreshtoken] ([id], [expiry_date], [token], [user_id]) VALUES (15, CAST(N'2023-04-19T23:43:59.4434662' AS DateTime2), N'dd80a3be-3fbe-4bdc-9610-391228772211', 2)
INSERT [dbo].[refreshtoken] ([id], [expiry_date], [token], [user_id]) VALUES (16, CAST(N'2023-04-19T23:58:31.0187021' AS DateTime2), N'c279edb9-3676-4d7b-8ae9-7cce568584cc', 2)
INSERT [dbo].[refreshtoken] ([id], [expiry_date], [token], [user_id]) VALUES (17, CAST(N'2023-04-20T00:01:34.6373109' AS DateTime2), N'edf0da6f-9e20-4c5b-8485-416657dce61a', 2)
INSERT [dbo].[refreshtoken] ([id], [expiry_date], [token], [user_id]) VALUES (18, CAST(N'2023-04-20T00:08:56.1305380' AS DateTime2), N'bb7cbe67-f06a-4764-bf39-f6a8ddebeb81', 2)
INSERT [dbo].[refreshtoken] ([id], [expiry_date], [token], [user_id]) VALUES (19, CAST(N'2023-04-20T00:11:47.7545022' AS DateTime2), N'529dece3-ccfa-4f64-9017-d2680d14e499', 2)
INSERT [dbo].[refreshtoken] ([id], [expiry_date], [token], [user_id]) VALUES (20, CAST(N'2023-04-20T00:24:08.8596666' AS DateTime2), N'7cf86bc9-2fe3-49fa-b419-7f8f02c0ac55', 2)
INSERT [dbo].[refreshtoken] ([id], [expiry_date], [token], [user_id]) VALUES (21, CAST(N'2023-04-20T00:28:44.5453976' AS DateTime2), N'9e312381-caec-42d6-be37-8bf6e5f772af', 2)
INSERT [dbo].[refreshtoken] ([id], [expiry_date], [token], [user_id]) VALUES (22, CAST(N'2023-04-20T00:40:33.3007587' AS DateTime2), N'66a69ffc-1d34-4b57-8904-23ebfe1f4347', 2)
INSERT [dbo].[refreshtoken] ([id], [expiry_date], [token], [user_id]) VALUES (23, CAST(N'2023-04-20T00:56:36.1549399' AS DateTime2), N'3e432835-a015-4c56-8f34-fd40b63a28bb', 2)
INSERT [dbo].[refreshtoken] ([id], [expiry_date], [token], [user_id]) VALUES (24, CAST(N'2023-04-20T01:04:51.0285952' AS DateTime2), N'daf3aa8c-6d5d-4754-bebe-f4a0ded62b12', 2)
INSERT [dbo].[refreshtoken] ([id], [expiry_date], [token], [user_id]) VALUES (25, CAST(N'2023-04-20T08:31:27.0699119' AS DateTime2), N'd9615772-d2b8-4d56-93ed-cdfe74510364', 2)
INSERT [dbo].[refreshtoken] ([id], [expiry_date], [token], [user_id]) VALUES (26, CAST(N'2023-04-20T08:31:59.9179068' AS DateTime2), N'b4045b49-2929-4806-830c-79b9859bc3ff', 1)
INSERT [dbo].[refreshtoken] ([id], [expiry_date], [token], [user_id]) VALUES (27, CAST(N'2023-04-20T08:33:55.3786585' AS DateTime2), N'55e3bf0f-433b-4e04-ab93-b4368fb86cb6', 2)
INSERT [dbo].[refreshtoken] ([id], [expiry_date], [token], [user_id]) VALUES (28, CAST(N'2023-04-20T08:34:25.6073477' AS DateTime2), N'adf2255f-8b6f-4d39-bde0-be88ba6b805d', 2)
INSERT [dbo].[refreshtoken] ([id], [expiry_date], [token], [user_id]) VALUES (29, CAST(N'2023-04-20T13:50:10.5798841' AS DateTime2), N'6aaddd1b-a550-4e00-b1f6-fca670eb36f6', 1)
INSERT [dbo].[refreshtoken] ([id], [expiry_date], [token], [user_id]) VALUES (30, CAST(N'2023-04-20T13:50:34.1479637' AS DateTime2), N'646858f1-5908-48a8-ba22-e1bf23612d35', 2)
INSERT [dbo].[refreshtoken] ([id], [expiry_date], [token], [user_id]) VALUES (31, CAST(N'2023-04-20T13:53:09.1689378' AS DateTime2), N'8b7a1b1c-935b-41f2-ad42-e0d89ada175a', 2)
INSERT [dbo].[refreshtoken] ([id], [expiry_date], [token], [user_id]) VALUES (32, CAST(N'2023-04-20T13:54:18.8007627' AS DateTime2), N'4b87069c-923f-4c91-86b9-1269742ee571', 1)
INSERT [dbo].[refreshtoken] ([id], [expiry_date], [token], [user_id]) VALUES (33, CAST(N'2023-04-20T15:53:38.3036167' AS DateTime2), N'ff2aa730-807e-445a-8ca8-9884ac3d2c14', 2)
INSERT [dbo].[refreshtoken] ([id], [expiry_date], [token], [user_id]) VALUES (34, CAST(N'2023-04-20T16:04:39.4579335' AS DateTime2), N'862961bf-b16b-465f-8724-3928bd846be5', 2)
GO
SET IDENTITY_INSERT [dbo].[roles] ON 

INSERT [dbo].[roles] ([id], [name]) VALUES (1, N'ROLE_ADMIN')
INSERT [dbo].[roles] ([id], [name]) VALUES (2, N'ROLE_TEACHER')
INSERT [dbo].[roles] ([id], [name]) VALUES (3, N'ROLE_USER')
SET IDENTITY_INSERT [dbo].[roles] OFF
GO
INSERT [dbo].[sinhvien] ([id], [lop_id]) VALUES (4, NULL)
INSERT [dbo].[sinhvien] ([id], [lop_id]) VALUES (5, NULL)
INSERT [dbo].[sinhvien] ([id], [lop_id]) VALUES (6, NULL)
GO
INSERT [dbo].[user_roles] ([user_id], [role_id]) VALUES (1, 1)
INSERT [dbo].[user_roles] ([user_id], [role_id]) VALUES (2, 2)
INSERT [dbo].[user_roles] ([user_id], [role_id]) VALUES (3, 3)
INSERT [dbo].[user_roles] ([user_id], [role_id]) VALUES (4, 3)
INSERT [dbo].[user_roles] ([user_id], [role_id]) VALUES (5, 3)
INSERT [dbo].[user_roles] ([user_id], [role_id]) VALUES (6, 3)
GO
SET IDENTITY_INSERT [dbo].[users] ON 

INSERT [dbo].[users] ([id], [address], [email], [hoten], [ngaysinh], [password], [phone], [username]) VALUES (1, N'Tay Ninh', N'admin@local', N'Truong Chi Tai', CAST(N'2001-09-27' AS Date), N'$2a$10$NdY6GUnBw/Vvn0jezfJDq.g/03AlIQIWoO3mv44YD.YZNyCYs90JO', N'0213456789', N'admin')
INSERT [dbo].[users] ([id], [address], [email], [hoten], [ngaysinh], [password], [phone], [username]) VALUES (2, N'Tay Ninh', N'teacher01@local', N'Truong Chi Tai', CAST(N'2001-09-27' AS Date), N'$2a$10$aR1mpe8CkGIGLCGqOENive4zgevQeCCOJzSZMLHqopvFfdyRnYg3q', N'0113456789', N'teacher01')
INSERT [dbo].[users] ([id], [address], [email], [hoten], [ngaysinh], [password], [phone], [username]) VALUES (3, N'Tay Ninh', N'student01@local', N'Truong Chi Tai', CAST(N'2001-09-27' AS Date), N'$2a$10$ABapnVlI6LZ7hiNTm8fnyOduaV36nYo7iY5Wz48a6g84931BLC1hS', N'0111456789', N'student01')
INSERT [dbo].[users] ([id], [address], [email], [hoten], [ngaysinh], [password], [phone], [username]) VALUES (4, N'Tay Ninh', N'student02@local', N'Truong Chi Tai', CAST(N'2001-09-27' AS Date), N'$2a$10$qIcWDQjQykodgvwYpF5UlOZM0z2ounpzXELJvW0LpJYKZaCgmd0HW', N'0111456781', N'student02')
INSERT [dbo].[users] ([id], [address], [email], [hoten], [ngaysinh], [password], [phone], [username]) VALUES (5, N'Tay Ninh', N'student03@local', N'Truong Chi Tai', CAST(N'2001-09-27' AS Date), N'$2a$10$ra4W/.UpXHSh3m3j3zhMa.uNhojdtebcoxOmyQgDc17Am9SaCxTkG', N'0111456711', N'student03')
INSERT [dbo].[users] ([id], [address], [email], [hoten], [ngaysinh], [password], [phone], [username]) VALUES (6, N'Tay Ninh', N'student04@local', N'Truong Chi Tai', CAST(N'2001-09-27' AS Date), N'$2a$10$U5XmxHdbDWfaH7yYhlrjJOUJe2014qahcXfe.MtHyicWd1Xm0nmzS', N'0411456711', N'student04')
SET IDENTITY_INSERT [dbo].[users] OFF
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UK_7ghqrto6257ql8yhj0q6ex0ce]    Script Date: 21-Apr-23 01:37:48 PM ******/
ALTER TABLE [dbo].[hedaotao] ADD  CONSTRAINT [UK_7ghqrto6257ql8yhj0q6ex0ce] UNIQUE NONCLUSTERED 
(
	[ten_hedaotao] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UK_namhoc_hocky]    Script Date: 21-Apr-23 01:37:48 PM ******/
ALTER TABLE [dbo].[hocky] ADD  CONSTRAINT [UK_namhoc_hocky] UNIQUE NONCLUSTERED 
(
	[namhoc] ASC,
	[hocky] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UK_mhk3iwf5q475fnudaiyjk977s]    Script Date: 21-Apr-23 01:37:48 PM ******/
ALTER TABLE [dbo].[khoa] ADD  CONSTRAINT [UK_mhk3iwf5q475fnudaiyjk977s] UNIQUE NONCLUSTERED 
(
	[ten_khoa] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UK_npjaj9v2m2rh9ok1eivd4l4jq]    Script Date: 21-Apr-23 01:37:48 PM ******/
ALTER TABLE [dbo].[lop] ADD  CONSTRAINT [UK_npjaj9v2m2rh9ok1eivd4l4jq] UNIQUE NONCLUSTERED 
(
	[ma_lop] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UK_ggnrxd88w9muj802j7k32qatu]    Script Date: 21-Apr-23 01:37:48 PM ******/
ALTER TABLE [dbo].[monhoc] ADD  CONSTRAINT [UK_ggnrxd88w9muj802j7k32qatu] UNIQUE NONCLUSTERED 
(
	[ma] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UK_vyflfe02a3incmf76pq9gdqs]    Script Date: 21-Apr-23 01:37:48 PM ******/
ALTER TABLE [dbo].[monhoc] ADD  CONSTRAINT [UK_vyflfe02a3incmf76pq9gdqs] UNIQUE NONCLUSTERED 
(
	[monhoc] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UK_4mm7b0f6wq372e4kdmix8aox6]    Script Date: 21-Apr-23 01:37:48 PM ******/
ALTER TABLE [dbo].[nganh] ADD  CONSTRAINT [UK_4mm7b0f6wq372e4kdmix8aox6] UNIQUE NONCLUSTERED 
(
	[ma_nganh] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UK_rx25cfgooetw23jq2wl3kbawb]    Script Date: 21-Apr-23 01:37:48 PM ******/
ALTER TABLE [dbo].[nganh] ADD  CONSTRAINT [UK_rx25cfgooetw23jq2wl3kbawb] UNIQUE NONCLUSTERED 
(
	[ten_nganh] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UK_2edy94mupqkd2d9cgtx9l9plk]    Script Date: 21-Apr-23 01:37:48 PM ******/
ALTER TABLE [dbo].[phong] ADD  CONSTRAINT [UK_2edy94mupqkd2d9cgtx9l9plk] UNIQUE NONCLUSTERED 
(
	[ten_phong] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UK_or156wbneyk8noo4jstv55ii3]    Script Date: 21-Apr-23 01:37:48 PM ******/
ALTER TABLE [dbo].[refreshtoken] ADD  CONSTRAINT [UK_or156wbneyk8noo4jstv55ii3] UNIQUE NONCLUSTERED 
(
	[token] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UK_ofx66keruapi6vyqpv6f2or37]    Script Date: 21-Apr-23 01:37:48 PM ******/
ALTER TABLE [dbo].[roles] ADD  CONSTRAINT [UK_ofx66keruapi6vyqpv6f2or37] UNIQUE NONCLUSTERED 
(
	[name] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UK_6dotkott2kjsp8vw4d0m25fb7]    Script Date: 21-Apr-23 01:37:48 PM ******/
ALTER TABLE [dbo].[users] ADD  CONSTRAINT [UK_6dotkott2kjsp8vw4d0m25fb7] UNIQUE NONCLUSTERED 
(
	[email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UK_du5v5sr43g5bfnji4vb8hg5s3]    Script Date: 21-Apr-23 01:37:48 PM ******/
ALTER TABLE [dbo].[users] ADD  CONSTRAINT [UK_du5v5sr43g5bfnji4vb8hg5s3] UNIQUE NONCLUSTERED 
(
	[phone] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UK_r43af9ap4edm43mmtq01oddj6]    Script Date: 21-Apr-23 01:37:48 PM ******/
ALTER TABLE [dbo].[users] ADD  CONSTRAINT [UK_r43af9ap4edm43mmtq01oddj6] UNIQUE NONCLUSTERED 
(
	[username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
ALTER TABLE [dbo].[diemdanh]  WITH CHECK ADD  CONSTRAINT [FKdbn3yj42swk7y30o2ic75ya4y] FOREIGN KEY([sinhvien_id])
REFERENCES [dbo].[sinhvien] ([id])
GO
ALTER TABLE [dbo].[diemdanh] CHECK CONSTRAINT [FKdbn3yj42swk7y30o2ic75ya4y]
GO
ALTER TABLE [dbo].[diemdanh]  WITH CHECK ADD  CONSTRAINT [FKopk8kba9lmx6w7665iic5q6or] FOREIGN KEY([loptinchi_id], [ngay_id])
REFERENCES [dbo].[loptinchingay] ([loptinchi_id], [ngay_id])
GO
ALTER TABLE [dbo].[diemdanh] CHECK CONSTRAINT [FKopk8kba9lmx6w7665iic5q6or]
GO
ALTER TABLE [dbo].[giangvien]  WITH CHECK ADD  CONSTRAINT [FKa2yba9xo0goxq3fp8ewuvwmqy] FOREIGN KEY([khoa_id])
REFERENCES [dbo].[khoa] ([id])
GO
ALTER TABLE [dbo].[giangvien] CHECK CONSTRAINT [FKa2yba9xo0goxq3fp8ewuvwmqy]
GO
ALTER TABLE [dbo].[giangvien]  WITH CHECK ADD  CONSTRAINT [FKf2l581omtplvh90sdha9x6owy] FOREIGN KEY([id])
REFERENCES [dbo].[users] ([id])
GO
ALTER TABLE [dbo].[giangvien] CHECK CONSTRAINT [FKf2l581omtplvh90sdha9x6owy]
GO
ALTER TABLE [dbo].[lop]  WITH CHECK ADD  CONSTRAINT [FK1cp71dxunaumm5rm3tdoc6wkb] FOREIGN KEY([giangvien_id])
REFERENCES [dbo].[giangvien] ([id])
GO
ALTER TABLE [dbo].[lop] CHECK CONSTRAINT [FK1cp71dxunaumm5rm3tdoc6wkb]
GO
ALTER TABLE [dbo].[lop]  WITH CHECK ADD  CONSTRAINT [FKd03bam894unft6iuxix18bnsy] FOREIGN KEY([nganh_id])
REFERENCES [dbo].[nganh] ([id])
GO
ALTER TABLE [dbo].[lop] CHECK CONSTRAINT [FKd03bam894unft6iuxix18bnsy]
GO
ALTER TABLE [dbo].[lop]  WITH CHECK ADD  CONSTRAINT [FKkvbck13opnoekaik8aovuqu22] FOREIGN KEY([hedaotao_id])
REFERENCES [dbo].[hedaotao] ([id])
GO
ALTER TABLE [dbo].[lop] CHECK CONSTRAINT [FKkvbck13opnoekaik8aovuqu22]
GO
ALTER TABLE [dbo].[loptinchi]  WITH CHECK ADD  CONSTRAINT [FKfeb8fdxd6uuq9b6e80kljlak4] FOREIGN KEY([phong_id])
REFERENCES [dbo].[phong] ([id])
GO
ALTER TABLE [dbo].[loptinchi] CHECK CONSTRAINT [FKfeb8fdxd6uuq9b6e80kljlak4]
GO
ALTER TABLE [dbo].[loptinchi]  WITH CHECK ADD  CONSTRAINT [FKhxadyn4h6vw5eh69y31edmk5d] FOREIGN KEY([giangvien_id])
REFERENCES [dbo].[giangvien] ([id])
GO
ALTER TABLE [dbo].[loptinchi] CHECK CONSTRAINT [FKhxadyn4h6vw5eh69y31edmk5d]
GO
ALTER TABLE [dbo].[loptinchi]  WITH CHECK ADD  CONSTRAINT [FKmn20xb9clfwutwu6h7apkj15b] FOREIGN KEY([monhoc_id])
REFERENCES [dbo].[monhoc] ([id])
GO
ALTER TABLE [dbo].[loptinchi] CHECK CONSTRAINT [FKmn20xb9clfwutwu6h7apkj15b]
GO
ALTER TABLE [dbo].[loptinchi]  WITH CHECK ADD  CONSTRAINT [FKqtho9gsmeatx3s1m14ciqgad4] FOREIGN KEY([hocky_id])
REFERENCES [dbo].[hocky] ([id])
GO
ALTER TABLE [dbo].[loptinchi] CHECK CONSTRAINT [FKqtho9gsmeatx3s1m14ciqgad4]
GO
ALTER TABLE [dbo].[loptinchi_lop]  WITH CHECK ADD  CONSTRAINT [FKi8xf7bp4dgp6e9ajco5wspcbd] FOREIGN KEY([lop_id])
REFERENCES [dbo].[lop] ([id])
GO
ALTER TABLE [dbo].[loptinchi_lop] CHECK CONSTRAINT [FKi8xf7bp4dgp6e9ajco5wspcbd]
GO
ALTER TABLE [dbo].[loptinchi_lop]  WITH CHECK ADD  CONSTRAINT [FKqrx89lxcdvesrxm3unv7a9gt4] FOREIGN KEY([loptinchi_id])
REFERENCES [dbo].[loptinchi] ([id])
GO
ALTER TABLE [dbo].[loptinchi_lop] CHECK CONSTRAINT [FKqrx89lxcdvesrxm3unv7a9gt4]
GO
ALTER TABLE [dbo].[loptinchi_sinhvien]  WITH CHECK ADD  CONSTRAINT [FKareuqm9blm21sv24hphvn7qb9] FOREIGN KEY([loptinchi_id])
REFERENCES [dbo].[loptinchi] ([id])
GO
ALTER TABLE [dbo].[loptinchi_sinhvien] CHECK CONSTRAINT [FKareuqm9blm21sv24hphvn7qb9]
GO
ALTER TABLE [dbo].[loptinchi_sinhvien]  WITH CHECK ADD  CONSTRAINT [FKjwj70bukvmksa9cbj7qh9vbm5] FOREIGN KEY([sinhvien_id])
REFERENCES [dbo].[sinhvien] ([id])
GO
ALTER TABLE [dbo].[loptinchi_sinhvien] CHECK CONSTRAINT [FKjwj70bukvmksa9cbj7qh9vbm5]
GO
ALTER TABLE [dbo].[loptinchingay]  WITH CHECK ADD  CONSTRAINT [FK16us4jprq2is4v9rir59yi7o2] FOREIGN KEY([loptinchi_id])
REFERENCES [dbo].[loptinchi] ([id])
GO
ALTER TABLE [dbo].[loptinchingay] CHECK CONSTRAINT [FK16us4jprq2is4v9rir59yi7o2]
GO
ALTER TABLE [dbo].[loptinchingay]  WITH CHECK ADD  CONSTRAINT [FK3a2ltbwr8ct52wb129swqt5cn] FOREIGN KEY([ngay_id])
REFERENCES [dbo].[ngay] ([id])
GO
ALTER TABLE [dbo].[loptinchingay] CHECK CONSTRAINT [FK3a2ltbwr8ct52wb129swqt5cn]
GO
ALTER TABLE [dbo].[nganh]  WITH CHECK ADD  CONSTRAINT [FKf19evb4wgijodpa6il4obpy2y] FOREIGN KEY([khoa_id])
REFERENCES [dbo].[khoa] ([id])
GO
ALTER TABLE [dbo].[nganh] CHECK CONSTRAINT [FKf19evb4wgijodpa6il4obpy2y]
GO
ALTER TABLE [dbo].[refreshtoken]  WITH CHECK ADD  CONSTRAINT [FKa652xrdji49m4isx38pp4p80p] FOREIGN KEY([user_id])
REFERENCES [dbo].[users] ([id])
GO
ALTER TABLE [dbo].[refreshtoken] CHECK CONSTRAINT [FKa652xrdji49m4isx38pp4p80p]
GO
ALTER TABLE [dbo].[sinhvien]  WITH CHECK ADD  CONSTRAINT [FK34mwtu0tx68f9dmc9r5ys20v1] FOREIGN KEY([id])
REFERENCES [dbo].[users] ([id])
GO
ALTER TABLE [dbo].[sinhvien] CHECK CONSTRAINT [FK34mwtu0tx68f9dmc9r5ys20v1]
GO
ALTER TABLE [dbo].[sinhvien]  WITH CHECK ADD  CONSTRAINT [FKtk7hpdgalrm8j57v0mwtihghv] FOREIGN KEY([lop_id])
REFERENCES [dbo].[lop] ([id])
GO
ALTER TABLE [dbo].[sinhvien] CHECK CONSTRAINT [FKtk7hpdgalrm8j57v0mwtihghv]
GO
ALTER TABLE [dbo].[user_roles]  WITH CHECK ADD  CONSTRAINT [FKh8ciramu9cc9q3qcqiv4ue8a6] FOREIGN KEY([role_id])
REFERENCES [dbo].[roles] ([id])
GO
ALTER TABLE [dbo].[user_roles] CHECK CONSTRAINT [FKh8ciramu9cc9q3qcqiv4ue8a6]
GO
ALTER TABLE [dbo].[user_roles]  WITH CHECK ADD  CONSTRAINT [FKhfh9dx7w3ubf1co1vdev94g3f] FOREIGN KEY([user_id])
REFERENCES [dbo].[users] ([id])
GO
ALTER TABLE [dbo].[user_roles] CHECK CONSTRAINT [FKhfh9dx7w3ubf1co1vdev94g3f]
GO
USE [master]
GO
ALTER DATABASE [test3] SET  READ_WRITE 
GO
