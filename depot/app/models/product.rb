class Product < ActiveRecord::Base
  has_many :line_items
  before_destroy :check_line_items

  validates :title, :description, presence: true
  validates :title, uniqueness: true
  validates :price, numericality: { greater_than_or_equal_to: 0.4}

  def self.latest
    Product.order(:updated_at).last
  end

  def check_line_items
    if line_items.empty?
      return true
    else
      errors.add(:has_line_items, 'Line items detected')
      return false
    end
  end
end
