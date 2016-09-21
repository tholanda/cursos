require 'test_helper'

class ProductTest < ActiveSupport::TestCase
  # test "the truth" do
  #   assert true
  # end

  fixtures :products

  test "Instancia inicial do Product" do
    product = Product.new
    assert product.invalid?
    # p product.errors[:title]
    assert product.errors[:title].any?
    assert product.errors[:description].any?
    assert product.errors[:price].any?
  end

  test "Instancia com preco 0" do
    # product = Product.new(title: "meu produto", description: "desc", price: 0)
    product = products(:produto_0)

    assert product.invalid?
    assert product.errors[:price].any?
  end

end
