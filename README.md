# taco-cloud-spring

./bin/artemis create --user taco --password cloud --role admin --require-login instances/taco-cloud

instances/taco-cloud/bin/artemis run

instances/taco-cloud/bin/artemis queue create --user taco --password cloud --auto-create-address --address tacocloud.order.queue --name tacocloud.order.queue --preserve-on-no-consumers --durable --anycast --url tcp://localhost:61616
