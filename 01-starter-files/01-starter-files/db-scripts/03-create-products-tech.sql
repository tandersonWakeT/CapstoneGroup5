
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `full-stack-ecommerce`.`product`;
DROP TABLE IF EXISTS `full-stack-ecommerce`.`product_category`;

-- -----------------------------------------------------
-- Table `full-stack-ecommerce`.`product_category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `full-stack-ecommerce`.`product_category` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `category_name` VARCHAR(255) NULL DEFAULT NULL,
  `thumbnail_url` VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE=InnoDB
AUTO_INCREMENT = 1;

-- -----------------------------------------------------
-- Table `full-stack-ecommerce`.`product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `full-stack-ecommerce`.`product` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `sku` VARCHAR(255) DEFAULT NULL,
  `name` VARCHAR(255) DEFAULT NULL,
  `description` VARCHAR(255) DEFAULT NULL,
  `unit_price` DECIMAL(13,2) DEFAULT NULL,
  `image_url` VARCHAR(255) DEFAULT NULL,
  `active` BIT DEFAULT 1,
  `units_in_stock` INT(11) DEFAULT NULL,
   `date_created` DATETIME(6) DEFAULT NULL,
  `last_updated` DATETIME(6) DEFAULT NULL,
  `category_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_category` (`category_id`),
  CONSTRAINT `fk_category` FOREIGN KEY (`category_id`) REFERENCES `product_category` (`id`)
) 
ENGINE=InnoDB
AUTO_INCREMENT = 1;


SET FOREIGN_KEY_CHECKS = 1;
-- -----------------------------------------------------
-- Categories
-- -----------------------------------------------------
INSERT INTO product_category(category_name, thumbnail_url) VALUES ('Keyboards', 'assets/images/category-thumbnails/keyboard.jpg');
INSERT INTO product_category(category_name, thumbnail_url) VALUES ('Mice', 'assets/images/category-thumbnails/mouse.jpg');
INSERT INTO product_category(category_name, thumbnail_url) VALUES ('Office Chairs', 'assets/images/category-thumbnails/office-chair.jpg');
INSERT INTO product_category(category_name, thumbnail_url) VALUES ('Headsets', 'assets/images/category-thumbnails/headset.jpg');

-- -----------------------------------------------------
-- Add sample data
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Add 10 Keyboard Products
-- -----------------------------------------------------

INSERT INTO product (sku, name, description, image_url, active, units_in_stock, unit_price, category_id, date_created) 
VALUES ('KBD-TECH-0001', 'RGB Gaming Mechanical Keyboard', 'The keyboard that is meant for gaming and has RGB lights for decorative purposes. Also has mechanical keys to help with gaming', 'assets/images/products/keyboards/keyboard-1000.jpg', 1, 100, 104.99, 1, NOW()); 

INSERT INTO product (sku, name, description, image_url, active, units_in_stock, unit_price, category_id, date_created) 
VALUES ('KBD-TECH-0002', 'Mechanical Keyboard', 'The keyboard has mechanical keys for a better feel and can be used for gaming. Only black color; No RGB', 'assets/images/products/keyboards/keyboard-1001.jpg', 1, 100, 59.99, 1, NOW()); 

INSERT INTO product (sku, name, description, image_url, active, units_in_stock, unit_price, category_id, date_created) 
VALUES ('KBD-TECH-0003', 'Office Keyboard', 'This is an keyboard that is services the simple need in the office and not too flashy for someone to steal from the work place. The keyboard is wireless and connects via bluetooth', 'assets/images/products/keyboards/keyboard-1002.jpg', 1, 100, 35.99, 1, NOW()); 

INSERT INTO product (sku, name, description, image_url, active, units_in_stock, unit_price, category_id, date_created) 
VALUES ('KBD-TECH-0004', 'Budget Keyboard', 'Keyboard made for people on the budget. Not the best feel or connectivity, but it gets the job done.', 'assets/images/products/keyboards/keyboard-1003.jpg', 1, 100, 19.99, 1, NOW()); 

INSERT INTO product (sku, name, description, image_url, active, units_in_stock, unit_price, category_id, date_created) 
VALUES ('KBD-TECH-0005', 'Ergonomic Keyboard', 'This wireless keyboard and mouse are designed to encourage good hand and wrist posture.The keyboard has a split dome shape to fit the curve of your hands.', 'assets/images/products/keyboards/keyboard-1004.jpg', 1, 100, 129.99, 1, NOW()); 

INSERT INTO product (sku, name, description, image_url, active, units_in_stock, unit_price, category_id, date_created) 
VALUES ('KBD-TECH-0006', 'RGB Gaming Ergonmic Keyboard', 'Gaming Keyboard with backlight RGB lights. It is a 60% compact keyboard setting itself for an ergonmic design', 'assets/images/products/keyboards/keyboard-1005.jpg', 1, 100, 79.99, 1, NOW()); 

INSERT INTO product (sku, name, description, image_url, active, units_in_stock, unit_price, category_id, date_created) 
VALUES ('KBD-TECH-0007', 'Ergonomic Mechanical Keyboard', 'Split keyboord design to eliminate shoulder and wrist pain. Keyboard contains mechanical keys for smoother typing', 'assets/images/products/keyboards/keyboard-1006.jpg', 1, 100, 189.99, 1, NOW()); 

INSERT INTO product (sku, name, description, image_url, active, units_in_stock, unit_price, category_id, date_created) 
VALUES ('KBD-TECH-0008', 'Office Mechancial Keyboard', 'This keyboard is for the office workplace and nothing to fancy. This keyboard has mechanical keys to have swifter typing and higher proficiency', 'assets/images/products/keyboards/keyboard-1007.jpg', 1, 100, 52.99, 1, NOW()); 

INSERT INTO product (sku, name, description, image_url, active, units_in_stock, unit_price, category_id, date_created) 
VALUES ('KBD-TECH-0009', 'Budget Mechanical Keyboard', 'Keyboard made for people who are on the budget but still want that smooth feeling while typing.', 'assets/images/products/keyboards/keyboard-1008.jpg', 1, 100, 35.99, 1, NOW()); 

INSERT INTO product (sku, name, description, image_url, active, units_in_stock, unit_price, category_id, date_created) 
VALUES ('KBD-TECH-0010', 'Budget RGB Gaming Keyboard', 'For Gamers who are under a budget but still want the same feeling. Contains RGB lights to match the RGB in the PC', 'assets/images/products/keyboards/keyboard-1009.jpg', 1, 100, 17.99, 1, NOW()); 


-- -----------------------------------------------------
-- Add 10 Mice Products
-- -----------------------------------------------------

INSERT INTO product (sku, name, description, image_url, active, units_in_stock, unit_price, category_id, date_created) 
VALUES ('MOS-TECH-0001', 'Budget Wired Mouse', 'Wired black mouse with right and left clicks and scroll wheel', 'assets/images/products/mice/mouse001.jpg', 1, 100, 9.99, 2, NOW());

INSERT INTO product (sku, name, description, image_url, active, units_in_stock, unit_price, category_id, date_created) 
VALUES ('MOS-TECH-0002', 'Wireless Black Mouse', 'Wireless black mouse with right and left clicks and scroll wheel', 'assets/images/products/mice/mouse002.jpg', 1, 100, 14.99, 2, NOW());

INSERT INTO product (sku, name, description, image_url, active, units_in_stock, unit_price, category_id, date_created) 
VALUES ('MOS-TECH-0003', 'Wired Black Grey Mouse', 'Wired black and grey mouse with right and left clicks and scroll wheel', 'assets/images/products/mice/mouse003.jpg', 1, 100, 9.99, 2, NOW());

INSERT INTO product (sku, name, description, image_url, active, units_in_stock, unit_price, category_id, date_created) 
VALUES ('MOS-TECH-0004', 'Exotic Antique Mouse', 'High quality antique styled mouse. Made with real mahagony', 'assets/images/products/mice/mouse004.jpg', 1, 100, 59.99, 2, NOW());

INSERT INTO product (sku, name, description, image_url, active, units_in_stock, unit_price, category_id, date_created) 
VALUES ('MOS-TECH-0005', 'Black and Gold Mouse', 'Black and gold wireless mouse with scroll sphere', 'assets/images/products/mice/mouse005.jpg', 1, 100, 19.99, 2, NOW());

INSERT INTO product (sku, name, description, image_url, active, units_in_stock, unit_price, category_id,date_created) 
VALUES ('MOS-TECH-0006', 'Red Wired Mouse', 'Uniquely crafted polygon mouse with USB receiver plug in', 'assets/images/products/mice/mouse006.jpg', 1, 100, 27.99, 2, NOW());

INSERT INTO product (sku, name, description, image_url, active, units_in_stock, unit_price, category_id, date_created) 
VALUES ('MOS-TECH-0007', 'Exotic Polygon Mouse', 'Wired red mouse with left and right clicks and scroll wheel', 'assets/images/products/mice/mouse007.jpg', 1, 100, 14.99, 2, NOW());

INSERT INTO product (sku, name, description, image_url, active, units_in_stock, unit_price, category_id, date_created) 
VALUES ('MOS-TECH-0008', 'Gold Mouse', 'Wireless gold and white mouse with right and left clicks and scroll wheel', 'assets/images/products/mice/mouse008.jpg', 1, 100, 19.99, 2, NOW());

INSERT INTO product (sku, name, description, image_url, active, units_in_stock, unit_price, category_id, date_created) 
VALUES ('MOS-TECH-0009', 'Wireless Grey Black Mouse', 'Wireless grey and black mouse with grip and USB receiver plug in', 'assets/images/products/mice/mouse009.jpg', 1, 100, 19.99, 2, NOW());

INSERT INTO product (sku, name, description, image_url, active, units_in_stock, unit_price, category_id, date_created) 
VALUES ('MOS-TECH-0010', 'Wireless Gaming Mouse', 'Light up LED gaming mouse with USB receiver plug in', 'assets/images/products/mice/mouse010.jpg', 1, 100, 34.99, 2, NOW());


-- -----------------------------------------------------
-- Add 10 Chair Products
-- -----------------------------------------------------

INSERT INTO product (sku, name, description, image_url, active, units_in_stock, unit_price, category_id, date_created)
VALUES ('CHR-TECH-0001', 'Neo Chair', 'This is a simple chair for the office. It provides basic comfort, back support but is nothing fancy or expensive.', 'assets/images/products/officechairs/NEO-CHAIR-c1.jpg' , 1, 100, 68.98, 3, NOW());

INSERT INTO product (sku, name, description, image_url, active, units_in_stock, unit_price, category_id, date_created)
VALUES ('CHR-TECH-0002', 'Ergonomic Office Chair', 'This chair is known for having good lumbar support and a sleek design. It works well in office or home settings and provides good support for long hours at the desk.', 'assets/images/products/officechairs/Office-Chair-Styled-c2.jpg' , 1, 100, 86.99, 3, NOW());

INSERT INTO product (sku, name, description, image_url, active, units_in_stock, unit_price, category_id, date_created)
VALUES ('CHR-TECH-0003', 'Budget Chair', 'In case you need a place to sit but don\'t have much free space in your budget. This is the perfect option for completing that office setup without breaking the bank.', 'assets/images/products/officechairs/Budget-Chair-c3.jpg' , 1, 100, 38.99, 3, NOW());

INSERT INTO product (sku, name, description, image_url, active, units_in_stock, unit_price, category_id, date_created)
VALUES ('CHR-TECH-0004', 'Swivel Chair', 'This is a good chair for tight spaces, with a relatively small frame and no arm or headrest it is easy to move around and will fit just about anywhere.', 'assets/images/products/officechairs/Swivel-Chair-c4.jpg' , 1, 100, 50.00, 3, NOW());

INSERT INTO product (sku, name, description, image_url, active, units_in_stock, unit_price, category_id, date_created)
VALUES ('CHR-TECH-0005', 'Mesh Swivel Chair', 'While very similar to the swivel chair in functionality, this one has a mesh upholstered seat giving it a different feel.', 'assets/images/products/officechairs/Mesh-Swivel-Chair-c5.jpg' , 1, 100, 43.99, 3, NOW());

INSERT INTO product (sku, name, description, image_url, active, units_in_stock, unit_price, category_id, date_created)
VALUES ('CHR-TECH-0006', 'GTPLAYER Gaming Chair', 'For the gamer enthusiasts out there this chair is the go to. It provides a comfortable headrest, lumbar support and a stylish look. Perfect for gaming or home office use.', 'assets/images/products/officechairs/GTPLAYER-gaming-chair-c6.jpg' , 1, 100, 110.00, 3, NOW());

INSERT INTO product (sku, name, description, image_url, active, units_in_stock, unit_price, category_id, date_created)
VALUES ('CHR-TECH-0007', 'Razer Ergonomic Gaming Chair', 'If money is no worry and gaming is your pastime, this is the greatest option around. It is stylish, comfortable to sit in for long durations, has High Density foam cushions and an ergonomically designed frame.', 'assets/images/products/officechairs/Razer-Gaming-Chair-c7.jpg' , 1, 100, 250.00, 3, NOW());

INSERT INTO product (sku, name, description, image_url, active, units_in_stock, unit_price, category_id, date_created)
VALUES ('CHR-TECH-0008', 'Luxurious Office Chair', 'For a competitively great price this luxurious and nicely styled chair is perfect for your home office set up. Its padded leather armrests and seats provide great comfort and feel and will compliment your office setup.', 'assets/images/products/officechairs/Luxurious-Office-Chair-c8.jpg' , 1, 100, 107.99, 3, NOW());

INSERT INTO product (sku, name, description, image_url, active, units_in_stock, unit_price, category_id, date_created)
VALUES ('CHR-TECH-0009', 'Exotic Office Chair', 'Add elegance to your office with this old fashion styled office chair. This will go great with a Mahogany Desk.', 'assets/images/products/officechairs/Exotic-Office-Chair-c9.jpg' , 1, 100, 350.00, 3, NOW());

INSERT INTO product (sku, name, description, image_url, active, units_in_stock, unit_price, category_id, date_created)
VALUES ('CHR-TECH-0010', 'Vinsetto Exotic Office Chair', 'This office chair provides great comfort, an exotic feel and style, and long lasting durability.', 'assets/images/products/officechairs/Vinsetto-Exotic-Office-Chair-c10.jpg' , 1, 100, 212.99, 3, NOW());




-- -----------------------------------------------------
-- Add 10 Headset Products
-- -----------------------------------------------------

INSERT INTO product (sku, name, description, image_url, active, units_in_stock, unit_price, category_id, date_created) 
VALUES ('HS-TECH-0001', 'H390 Wired Headset', 'Experience clear internet calls a with simple plug-and-play USB connection and a noise-canceling mic. Rigid left-sided mic boom is moveable and can be tucked out of the way when you are not using it.', 'assets/images/products/headsets/h390-wiredheadset-c1.jpg', 1, 30, 25.99, 4, NOW()); 

INSERT INTO product (sku, name, description, image_url, active, units_in_stock, unit_price, category_id, date_created) 
VALUES ('HS-TECH-0002', 'Ultralight Gaming Headset', 'The Ultralight is a multi-platform gaming headset built with superior comfort, Extended durability and crystal-clear sound.', 'assets/images/products/headsets/ultralight-gaming-headset-c2.jpg', 1, 25, 30.99, 4, NOW()); 

INSERT INTO product (sku, name, description, image_url, active, units_in_stock, unit_price, category_id, date_created) 
VALUES ('HS-TECH-0003', 'Cyber Acoustics Headset', 'The Cyber Acoustics is a quality stereo headset with a unidirectional, noise cancelling microphone. Perfect for today s demanding classrooms and open office environments.', 'assets/images/products/headsets/cyber-acoustics-headset-c3.jpg', 1, 40, 32.99, 4, NOW()); 

INSERT INTO product (sku, name, description, image_url, active, units_in_stock, unit_price, category_id, date_created) 
VALUES ('HS-TECH-0004', 'Fiodio Gaming Headset', 'Supreme All-around Sound: Immersive 7.1 surround sound, premium 50MM neodymium driver with an expanded frequency range of 20 Hz - 20, 000 Hz.', 'assets/images/products/headsets/fiodio-gaming-headset-c4.jpg', 1, 15, 30.99, 4, NOW()); 

INSERT INTO product (sku, name, description, image_url, active, units_in_stock, unit_price, category_id, date_created) 
VALUES ('HS-TECH-0005', 'Polo Gaming Headset', 'Supreme audio quality: Large 53 millimeter drivers provide high-quality audio. Hear in-game details better and get the in-game advantage on your opponents', 'assets/images/products/headsets/polo-gaming-headset-c5.jpg', 1, 50, 45.99, 4, NOW()); 

INSERT INTO product (sku, name, description, image_url, active, units_in_stock, unit_price, category_id, date_created) 
VALUES ('HS-TECH-0006', 'Corded Office Headset', 'With a comfy and secure dual-ear wearing style, the Corded Office Headset offers total focus, clearer sound, and a snug fit.', 'assets/images/products/headsets/corded-office-headset-c6.jpg', 1, 70, 22.99, 4, NOW()); 

INSERT INTO product (sku, name, description, image_url, active, units_in_stock, unit_price, category_id, date_created) 
VALUES ('HS-TECH-0007', 'Budget High Performance Headset', 'Logitech USB Headset H540. Plug into high-performance audio for PC calls, music, and more. Simply insert the USB and instantaneously experience crisp, digital stereo and crystal-clear calls.', 'assets/images/products/headsets/budget-high-performance-headset-c7.jpg', 1, 60, 10.99, 4, NOW()); 

INSERT INTO product (sku, name, description, image_url, active, units_in_stock, unit_price, category_id, date_created) 
VALUES ('HS-TECH-0008', 'Ergonomic USB Gaming Headset', 'The HP Wired Headphones have a built-in volume control and an adjustable omni-directional microphone. In addition, the headband adapts to different head sizes.', 'assets/images/products/headsets/ergonomic-usb-gaming-headset-c8.jpg', 1, 30, 25.99, 4, NOW()); 

INSERT INTO product (sku, name, description, image_url, active, units_in_stock, unit_price, category_id, date_created) 
VALUES ('HS-TECH-0009', 'Budget Office Headset', 'Budget Office headset with Microphone, Foldable Over Ear Bluetooth V5.0 Headset with Micro SD/TF Port, HiFi Stereo Sound, Ergonomic Design for Music Calls Working Home Smartphone TV Laptop.', 'assets/images/products/headsets/budget-office-headset-c9.jpg', 1, 40, 8.99, 4, NOW()); 

INSERT INTO product (sku, name, description, image_url, active, units_in_stock, unit_price, category_id, date_created) 
VALUES ('HS-TECH-0010', 'EPOS Ergonomic Gaming Headset', 'The EPOS Ergonomic Gaming Headset is created for the most passionate gamers. The latest colour variant in our portfolio, the headset comes in clean, crisp white, with both white and contrasted bronze-coloured cover plates included.', 'assets/images/products/headsets/epos-ergonomic-gaming-headset-c10.jpg', 1, 15, 87.99, 4, NOW());

