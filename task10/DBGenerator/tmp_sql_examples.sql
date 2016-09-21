/* count user friends */
select u.id, u.name, u.surname, u.birthdate, COUNT(u.id) from users as u
join friendships as f on u.id = f.userid1
group by u.id;


select p.id as post_id, l.timestamp as like_timestamp, u.id as user_id, u.name as user_name, u.surname as user_surname, 
COUNT(u.id) as likes_count
from posts as p
join likes as l on l.postid = p.id
join users as u on p.userId = u.id
group by u.id 

select u.id as user_id, u.name as user_name, u.surname as user_surname, 
COUNT(u.id) as likes_count
from posts as p
join likes as l on l.postid = p.id
join users as u on p.userId = u.id
group by u.id 


select p.id as post_id, l.timestamp as like_timestamp, u.id as user_id, u.name as user_name, u.surname as user_surname 
from posts as p
join likes as l on l.postid = p.id
join users as u on p.userId = u.id
where l.timestamp >= '2015-05-01 00:00:00' and l.timestamp <= '2015-05-31 23:59:59'
order by u.id

/* count user likes in may 2015 */
select u.id as user_id, u.name as user_name, u.surname as user_surname, 
COUNT(u.id) as likes_count
from posts as p
join likes as l on l.postid = p.id
join users as u on p.userId = u.id
where l.timestamp >= '2015-05-01 00:00:00' and l.timestamp <= '2015-05-31 23:59:59'
group by u.id

select ulc.user_id, ulc.user_name, ulc.user_surname, ulc.likes_count 
from 
(
	select u.id as user_id, u.name as user_name, u.surname as user_surname, 
	COUNT(u.id) as likes_count
	from posts as p
	join likes as l on l.postid = p.id
	join users as u on p.userId = u.id
	where l.timestamp >= '2015-05-01 00:00:00' and l.timestamp <= '2015-05-31 23:59:59'
	group by u.id
) as ulc
join friendships as f on f.userid1 = ulc.user_id

/* count likes and friends */
select ulc.user_id, ulc.user_name, ulc.user_surname, ulc.likes_count, COUNT(ulc.user_id) as friends_count 
from 
(
	select u.id as user_id, u.name as user_name, u.surname as user_surname, 
	COUNT(u.id) as likes_count
	from posts as p
	join likes as l on l.postid = p.id
	join users as u on p.userId = u.id
	where l.timestamp >= '2015-05-01 00:00:00' and l.timestamp <= '2015-05-31 23:59:59'
	group by u.id
) as ulc
join friendships as f on f.userid1 = ulc.user_id
group by ulc.user_id


/* FINAL QUERY */
 select * from 
 (
	select ulc.user_id, ulc.user_name, ulc.user_surname, ulc.likes_count, COUNT(ulc.user_id) as friends_count 
	from 
	(
		select u.id as user_id, u.name as user_name, u.surname as user_surname, 
		COUNT(u.id) as likes_count
		from posts as p
		join likes as l on l.postid = p.id
		join users as u on p.userId = u.id
		where l.timestamp >= '2015-05-01 00:00:00' and l.timestamp <= '2015-05-31 23:59:59'
		group by u.id
	) as ulc
	join friendships as f on f.userid1 = ulc.user_id
	group by ulc.user_id
 ) as result
 where result.likes_count > 20 and result.friends_count > 100
 order by result.likes_count desc, result.friends_count desc