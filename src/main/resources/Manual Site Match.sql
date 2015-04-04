
Select * from public.sites_location sl
inner join public.geo_location gl on sl.geo_location_id = gl.id
where sites_id = 70

Select * from public.geo_location gl
inner join public.geo_location_parent glp on gl.id = glp.geo_location_id
inner join public.geo_location parentGl on glp.geo_location_parent_id = parentGl.id
where gl.name = 'Miami%'
and gl.geo_location_type_id = 5
and parentGl.code = 'FL'



Select * from Sites
where name = 'south florida'

Select * from public.sites_location sl
where sl.geo_location_id = 2891