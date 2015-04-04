
-- Duplicate City Names
Select gl.name, parentGl.code, count(*) from public.geo_location gl
inner join public.geo_location_parent glp on gl.id = glp.geo_location_id
inner join public.geo_location parentGl on glp.geo_location_parent_id = parentGl.id
where parentGl.geo_location_type_id = 2
and gl.geo_location_type_id = 3
group by gl.name, parentGl.code
having count(*) > 1;

-- Duplicate County Names
Select gl.name, parentGl.code, count(*) from public.geo_location gl
inner join public.geo_location_parent glp on gl.id = glp.geo_location_id
inner join public.geo_location parentGl on glp.geo_location_parent_id = parentGl.id
where parentGl.geo_location_type_id = 2
and gl.geo_location_type_id = 4
group by gl.name, parentGl.code
having count(*) > 1;

-- Checek if Same Geo Maps to multiple Sites 
Select gl.id, count(*) from sites_location sl
inner join public.geo_location gl on sl.geo_location_id = gl.id
where gl.geo_location_type_id not in (1,2)
group by gl.id
having count(*) > 1