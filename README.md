<h1>Car Speed Monitoring System for Android Automotive OS (AAOS)</h1>
<h2>Overview</h2>
<p>This repository implements a <strong>Car Speed Monitoring System</strong> designed for Android Automotive OS (AAOS). The system monitors vehicle speed using the Android Car API and alerts both the user and the car rental company when the car&apos;s speed exceeds a predefined limit. The solution uses Firebase for real-time notifications to the rental company and includes modular components for speed monitoring, alerting, and notification delivery.</p>
<h2>Features</h2>
<ul>
    <li>
        <p><strong>Speed Monitoring</strong>: Tracks the vehicle&apos;s current speed through the Android Car API&apos;s Vehicle HAL.</p>
    </li>
    <li>
        <p><strong>Speed Limit Management</strong>: Allows the fleet company to set a customer-specific speed limit.</p>
    </li>
    <li>
        <p><strong>Firebase Integration</strong>: Sends notifications to Firebase Realtime Database when the speed limit is violated.</p>
    </li>
    <li>
        <p><strong>User Alerts</strong>: Displays warnings to the user when the speed limit is exceeded.</p>
    </li>
</ul>
<p><br></p>
<h2>Modules Overview</h2>
<h3><strong>MainActivity</strong></h3>
<p>The <code>MainActivity</code> class is responsible for setting up the speed monitoring functionality of the application. It contains the <code>speedMonitorTool()</code> method, which:</p>
<ul>
    <li>
        <p>Initializes and configures the <code>SpeedMonitoringModule</code>.</p>
    </li>
    <li>
        <p>Sets the customer ID and defines the speed limit for the specific customer.</p>
    </li>
    <li>
        <p>Integrates the system with the Android Automotive OS environment using the Car API.</p>
    </li>
</ul>
<p><br></p>
<h3><strong>SpeedMonitoringModule</strong></h3>
<p>The <code>SpeedMonitoringModule</code> class handles:</p>
<ul>
    <li>
        <p><strong>Speed Monitoring</strong>: It fetches real-time speed data from the Vehicle HAL using the Car API.</p>
    </li>
    <li>
        <p><strong>Alerting</strong>: If the car&apos;s speed exceeds the defined limit, it triggers alerts for the user and notifies the rental company.</p>
    </li>
    <li>
        <p><strong>Modular Design</strong>: Utilizes helper classes like <code>SpeedMonitor</code>, <code>UserAlert</code>, and <code>FirebaseNotificationSender</code> to delegate tasks and maintain clean, modular code.</p>
    </li>
</ul>
<p>Key Responsibilities:</p>
<ol start="1">
    <li>
        <p>Interact with the car&apos;s hardware to obtain speed data.</p>
    </li>
    <li>
        <p>Determine if the speed limit is violated using the <code>SpeedMonitor</code> class.</p>
    </li>
    <li>
        <p>Notify the user via <code>UserAlert</code> and send updates to Firebase using <code>FirebaseNotificationSender</code>.</p>
    </li>
</ol>
<p><br></p>
<h3><strong>FirebaseNotificationSender</strong></h3>
<p>The <code>FirebaseNotificationSender</code> class is responsible for:</p>
<ul>
    <li>
        <p>Sending notifications to Firebase Realtime Database when the car&apos;s speed exceeds the limit.</p>
    </li>
    <li>
        <p>Accepting input such as customer ID and current speed.</p>
    </li>
    <li>
        <p>Creating a notification data object to be stored under the customer&apos;s ID in Firebase.</p>
    </li>
</ul>
<p>Logs indicate whether the notification operation succeeded or failed.</p>
<p><br></p>
<h3><strong>SpeedMonitor</strong></h3>
<p>The <code>SpeedMonitor</code> class contains the logic to determine if a vehicle is speeding. Its primary method, <code>isSpeeding()</code>, checks:</p>
<ul>
    <li>
        <p><strong>Inputs</strong>: Current speed and predefined speed limit.</p>
    </li>
    <li>
        <p><strong>Output</strong>: Returns <code>true</code> if the car is speeding, otherwise returns <code>false</code>.</p>
    </li>
</ul>
<p><br></p>
<h3><strong>UserAlert</strong></h3>
<p>The <code>UserAlert</code> class is responsible for displaying alerts to the user when the speed limit is exceeded. It uses an <code>AlertDialog</code> to show:</p>
<ul>
    <li>
        <p>A message indicating that the speed limit has been violated, along with the specific speed limit value.</p>
    </li>
    <li>
        <p>An &quot;OK&quot; button for dismissing the dialog.</p>
    </li>
</ul>
<p>If the context is null, it logs an error message instead of showing the alert dialog.</p>


<p style="text-align: left;color: rgb(29, 34, 40);background-color: rgb(255, 255, 255);font-size: 16px;font-family: Tahoma, Arial, Helvetica, sans-serif;"><span style="color: black;font-family: serif;">Kind Regards,</span></p>
<p style="text-align: left;color: rgb(29, 34, 40);background-color: rgb(255, 255, 255);font-size: 16px;font-family: Tahoma, Arial, Helvetica, sans-serif;"><span style="color: black;font-family: serif;"><strong>Sairamkrishna Mammahe</strong></span><span style="color: black;font-family: serif;"><br></span><span style="font-family: serif;">Oracle Java Certified Developer</span><span style="font-family: serif;"><br>Google Android Certified Developer.</span></p>
