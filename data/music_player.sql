--select * from singer
--select * from song_singer
--select * from song
--select * from account
--select * from account_role
--select * from role
--select * from permission
--select * from role_permission
--delete from account_role
--delete from role_permission
--delete from account
--delete from permission
--delete from role
--select * from invalidated_token

if exists(select * from sys.objects where name = 'trigger_delete_song_singer')
	drop trigger trigger_delete_song_singer
go

create trigger trigger_delete_song_singer
on song_singer
after delete
as begin
	declare @deletedSongId int;
	select @deletedSongId = deleted.song_id from deleted;
	if(not exists(select * from song_singer where song_singer.song_id = @deletedSongId))
		begin
			delete from song where song.id = @deletedSongId;
		end
end