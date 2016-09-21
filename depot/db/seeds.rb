# This file should contain all the record creation needed to seed the database with its default values.
# The data can then be loaded with the rake db:seed (or created alongside the db with db:setup).
#
# Examples:
#
#   cities = City.create([{ name: 'Chicago' }, { name: 'Copenhagen' }])
#   Mayor.create(name: 'Emanuel', city: cities.first)

Product.delete_all

Product.create!(title: 'Computador' , description: 'pc', price: 10.0)
Product.create!(title: 'Bola' , description: 'bola campo', price: 11.0)
Product.create!(title: 'Raquete' , description: 'raquete tenis', price: 12.0)