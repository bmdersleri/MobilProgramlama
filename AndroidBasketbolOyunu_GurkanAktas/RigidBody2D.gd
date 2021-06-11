extends RigidBody2D

var holding = false
var first_pos
var second_pos
var vector3

var kosul1 = false
var kosul2 = false
var sayac = 0
signal skor

func _physics_process(delta):
	
#	hızı çok yavaşladığında hızı sıfır olur.
	if position.y >= 476 and abs(linear_velocity.y) < 5:
		linear_velocity.y = 0
		
	if kosul2:
		print("sayi",sayac)
		sayac += 1
		kosul2 = false
		emit_signal("skor",sayac)
		
		
		
func _unhandled_input(event):
		
#	mouse ile kontrol
#	if event.is_action_pressed("mouseLeft"):
#		first_pos = get_global_mouse_position()
#		holding = true
#
#	if event.is_action_released("mouseLeft"):
#		holding = false
#		shoot(vector3)
#
		
	if event is InputEventScreenTouch:
		if event.pressed:
			_on_touch_pressed(event)
		else:
			_on_touch_released(event)

func _on_touch_pressed(event):
	print("giris",event.position)
	first_pos = event.position
	
func _on_touch_released(event):
	print("cikis",event.position)
	second_pos = event.position
	shoot(second_pos - first_pos)
	
func update_moves() ->void:
	second_pos = get_global_mouse_position()
	vector3 = second_pos -first_pos
	

func shoot(vector):
	linear_velocity = -vector*5
	
func _on_giris_body_entered(body):
	kosul1 = true

func _on_giris_body_exited(body):
	kosul1 = false

func _on_cikis_body_entered(body):
	if kosul1:
		kosul2 = true
		kosul1 = false
