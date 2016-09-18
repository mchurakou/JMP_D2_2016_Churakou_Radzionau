/* count user friends */
select u.id, u.name, u.surname, u.birthdate, COUNT(u.id) from users as u
join friendships as f on u.id = f.userid1
group by u.id;