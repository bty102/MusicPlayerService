USE [music_player]
GO
/****** Object:  Table [dbo].[account]    Script Date: 4/5/2025 6:40:22 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[account](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[account_name] [nvarchar](20) NOT NULL,
	[password] [nvarchar](100) NOT NULL,
 CONSTRAINT [PK_account] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[account_role]    Script Date: 4/5/2025 6:40:22 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[account_role](
	[account_id] [int] NOT NULL,
	[role_id] [int] NOT NULL,
 CONSTRAINT [PK_account_role] PRIMARY KEY CLUSTERED 
(
	[account_id] ASC,
	[role_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[invalidated_token]    Script Date: 4/5/2025 6:40:22 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[invalidated_token](
	[id] [nvarchar](255) NOT NULL,
	[expiry_time] [datetime] NULL,
 CONSTRAINT [PK_invalidated_token] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[permission]    Script Date: 4/5/2025 6:40:22 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[permission](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](50) NOT NULL,
	[describe] [nvarchar](100) NULL,
 CONSTRAINT [PK_permission] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[role]    Script Date: 4/5/2025 6:40:22 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[role](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](50) NOT NULL,
	[describe] [nvarchar](100) NULL,
 CONSTRAINT [PK_role] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[role_permission]    Script Date: 4/5/2025 6:40:22 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[role_permission](
	[role_id] [int] NOT NULL,
	[permission_id] [int] NOT NULL,
 CONSTRAINT [PK_role_permission] PRIMARY KEY CLUSTERED 
(
	[role_id] ASC,
	[permission_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[singer]    Script Date: 4/5/2025 6:40:22 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[singer](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](100) NOT NULL,
 CONSTRAINT [PK_singer] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[song]    Script Date: 4/5/2025 6:40:22 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[song](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](100) NOT NULL,
	[image] [nvarchar](1000) NULL,
	[path] [nvarchar](1000) NOT NULL,
	[lyrics] [nvarchar](2500) NULL,
 CONSTRAINT [PK_song] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[song_singer]    Script Date: 4/5/2025 6:40:22 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[song_singer](
	[song_id] [int] NOT NULL,
	[singer_id] [int] NOT NULL,
	[is_ft] [bit] NOT NULL,
 CONSTRAINT [PK_song_singer] PRIMARY KEY CLUSTERED 
(
	[song_id] ASC,
	[singer_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[song_singer] ADD  CONSTRAINT [DF_song_singer_is_ft]  DEFAULT ((0)) FOR [is_ft]
GO
ALTER TABLE [dbo].[account_role]  WITH CHECK ADD  CONSTRAINT [FK_account_role_account] FOREIGN KEY([account_id])
REFERENCES [dbo].[account] ([id])
GO
ALTER TABLE [dbo].[account_role] CHECK CONSTRAINT [FK_account_role_account]
GO
ALTER TABLE [dbo].[account_role]  WITH CHECK ADD  CONSTRAINT [FK_account_role_role] FOREIGN KEY([role_id])
REFERENCES [dbo].[role] ([id])
GO
ALTER TABLE [dbo].[account_role] CHECK CONSTRAINT [FK_account_role_role]
GO
ALTER TABLE [dbo].[role_permission]  WITH CHECK ADD  CONSTRAINT [FK_role_permission_permission] FOREIGN KEY([permission_id])
REFERENCES [dbo].[permission] ([id])
GO
ALTER TABLE [dbo].[role_permission] CHECK CONSTRAINT [FK_role_permission_permission]
GO
ALTER TABLE [dbo].[role_permission]  WITH CHECK ADD  CONSTRAINT [FK_role_permission_role] FOREIGN KEY([role_id])
REFERENCES [dbo].[role] ([id])
GO
ALTER TABLE [dbo].[role_permission] CHECK CONSTRAINT [FK_role_permission_role]
GO
ALTER TABLE [dbo].[song_singer]  WITH CHECK ADD  CONSTRAINT [FK_song_singer_singer] FOREIGN KEY([singer_id])
REFERENCES [dbo].[singer] ([id])
GO
ALTER TABLE [dbo].[song_singer] CHECK CONSTRAINT [FK_song_singer_singer]
GO
ALTER TABLE [dbo].[song_singer]  WITH CHECK ADD  CONSTRAINT [FK_song_singer_song] FOREIGN KEY([song_id])
REFERENCES [dbo].[song] ([id])
GO
ALTER TABLE [dbo].[song_singer] CHECK CONSTRAINT [FK_song_singer_song]
GO
ALTER TABLE [dbo].[account]  WITH CHECK ADD  CONSTRAINT [CK_account_name] CHECK  ((len([account_name])>=(3)))
GO
ALTER TABLE [dbo].[account] CHECK CONSTRAINT [CK_account_name]
GO
ALTER TABLE [dbo].[account]  WITH CHECK ADD  CONSTRAINT [CK_account_password] CHECK  ((len([password])>=(3)))
GO
ALTER TABLE [dbo].[account] CHECK CONSTRAINT [CK_account_password]
GO
ALTER TABLE [dbo].[permission]  WITH CHECK ADD  CONSTRAINT [CK_permission_name] CHECK  ((len([name])>=(1)))
GO
ALTER TABLE [dbo].[permission] CHECK CONSTRAINT [CK_permission_name]
GO
ALTER TABLE [dbo].[role]  WITH CHECK ADD  CONSTRAINT [CK_role_name] CHECK  ((len([name])>=(1)))
GO
ALTER TABLE [dbo].[role] CHECK CONSTRAINT [CK_role_name]
GO
ALTER TABLE [dbo].[singer]  WITH CHECK ADD  CONSTRAINT [CK_singer_name] CHECK  ((len([name])>(0)))
GO
ALTER TABLE [dbo].[singer] CHECK CONSTRAINT [CK_singer_name]
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'account_name must be at least 3 char' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'account', @level2type=N'CONSTRAINT',@level2name=N'CK_account_name'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'account.password must be at least 3 char' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'account', @level2type=N'CONSTRAINT',@level2name=N'CK_account_password'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'permission.name must be at least 1 char' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'permission', @level2type=N'CONSTRAINT',@level2name=N'CK_permission_name'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'role.name must be at least 1 char' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'role', @level2type=N'CONSTRAINT',@level2name=N'CK_role_name'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'singer name must be at least 1 char' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'singer', @level2type=N'CONSTRAINT',@level2name=N'CK_singer_name'
GO
