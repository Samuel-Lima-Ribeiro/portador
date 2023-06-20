local-env-create:
	docker-compose -f stack.yaml up -d
	sleep 3
	docker cp data/table.sql postgresPortador:/var/lib/postgresql/data
	docker exec postgresPortador psql -h localhost -U admin -d postgres -a -f ./var/lib/postgresql/data/table.sql

local-env-destroy:
	docker-compose -f stack.yaml down